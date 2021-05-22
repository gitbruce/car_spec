package com.bruce.car.service

import com.bruce.car.entity.Country
import io.quarkus.vertx.web.Body
import io.quarkus.vertx.web.Param
import io.quarkus.vertx.web.Route
import org.jboss.logging.Logger
import javax.inject.Inject
import io.quarkus.vertx.web.Route.HandlerType.FAILURE
import io.quarkus.vertx.web.RouteBase
import io.smallrye.mutiny.Uni
import io.vertx.core.http.HttpMethod.DELETE
import io.vertx.core.http.HttpMethod.GET
import io.vertx.core.http.HttpMethod.POST
import io.vertx.core.http.HttpMethod.PUT
import io.vertx.core.http.HttpServerResponse
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.RoutingContext
import org.hibernate.reactive.mutiny.Mutiny
import java.lang.IllegalArgumentException
import java.util.function.Supplier


@RouteBase(path = "/countries", produces = ["application/json"])
class CountryService {
    val LOGGER: Logger = Logger.getLogger(CountryService::class.java.getName())

    @Inject
    lateinit var session: Mutiny.Session

    @Route(methods = [GET], path = "/")
    fun getAll(): Uni<List<Country>> {
        // In this case, it makes sense to return a Uni<List<Fruit>> because we return a reasonable amount of results
        // Consider returning a Multi<Fruit> for result streams
        val countries : Uni<List<Country>> = session.createNamedQuery(Country.FIND_ALL, Country::class.java).resultList
//        LOGGER.info("countries = " + countries)
        return countries
    }

    @Route(methods = [GET], path = "/:id")
    fun getSingle(@Param id: String): Uni<Country> {
        return session.find(Country::class.java, Integer.valueOf(id))
    }

    @Route(methods = [POST], path = "/")
    fun create(@Body country: Country, response: HttpServerResponse): Uni<Country> {
        return if (country == null) {
            Uni.createFrom().failure(IllegalArgumentException("Fruit id invalidly set on request."))
        } else session.persist(country)
            .chain(session::flush)
            .onItem().transform {
                response.statusCode = 201
                country
            }
    }

    @Route(methods = [PUT], path = "/:id")
    fun update(@Body fruit: Country, @Param id: String): Uni<Country> {
        return if (fruit == null || fruit.name == null) {
            Uni.createFrom().failure(IllegalArgumentException("Fruit name was not set on request."))
        } else session.find(Country::class.java, Integer.valueOf(id)) // If entity exists then update
            .onItem().ifNotNull().transformToUni { entity : Country ->
                entity.name = fruit.name
                session.flush()
                    .onItem().transform { entity }
            } // else
            .onItem().ifNull().fail()
    }

    @Route(methods = [DELETE], path = "/:id")
    fun delete(@Param id: String, response: HttpServerResponse): Uni<Country> {
        return session.find(Country::class.java, Integer.valueOf(id)) // If entity exists then delete
            .onItem().ifNotNull().transformToUni { entity ->
                session.remove(entity)
                    .chain(Supplier { session.flush() })
                    .map {
                        response.setStatusCode(204).end()
                        entity
                    }
            } // else
            .onItem().ifNull().fail()
    }

    @Route(path = "/*", type = FAILURE)
    fun error(context: RoutingContext) {
        val t = context.failure()
        if (t != null) {
            LOGGER.error("Failed to handle request", t)
            var status = context.statusCode()
            var chunk: String? = ""
            if (t is NoSuchElementException) {
                status = 404
            } else if (t is IllegalArgumentException) {
                status = 422
                chunk = JsonObject().put("code", status)
                    .put("exceptionType", t.javaClass.name).put("error", t.message).encode()
            }
            context.response().setStatusCode(status).end(chunk)
        } else {
            // Continue with the default error handler
            context.next()
        }
    }

}