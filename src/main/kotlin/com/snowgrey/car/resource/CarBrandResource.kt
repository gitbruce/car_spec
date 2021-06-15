package com.snowgrey.car.resource

import com.snowgrey.car.entity.CarBrand
import com.snowgrey.util.SnowFlake
import com.fasterxml.jackson.databind.ObjectMapper
import com.snowgrey.car.service.CarBrandService
import io.quarkus.panache.common.Sort
import io.vertx.mutiny.core.Vertx
import org.jboss.logging.Logger
import org.jboss.resteasy.annotations.jaxrs.PathParam
import java.lang.Exception
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.transaction.Transactional
import javax.ws.rs.*
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Path("brands")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
class CarBrandResource {
    @Inject
    lateinit var service: CarBrandService

    @GET
    fun get(): List<CarBrand> {
        return service.get()
    }

    @GET
    @Path("{id}")
    fun getSingle(@PathParam id: Long): CarBrand {
        return service.getSingle(id)
    }

    @POST
    @Transactional
    fun create(item: CarBrand): Response {
        return service.create(item)
    }

    @PUT
    @Path("{id}")
    @Transactional
    fun update(@PathParam id: Long, item: CarBrand): CarBrand {
        if (item.name == "") {
            throw WebApplicationException("brand Name was not set on request.", 422)
        }
        val entity: CarBrand =
            CarBrand.findById(id) ?: throw WebApplicationException("item with id of $id does not exist.", 404)
        entity.name = item.name
        entity.country?.id = item.country?.id
        entity.countryName = item.countryName
        entity.logo = item.logo
        return entity
    }

    @DELETE
    @Path("{id}")
    @Transactional
    fun delete(@PathParam id: Long): Response {
        val entity: CarBrand =
            CarBrand.findById(id) ?: throw WebApplicationException("item with id of $id does not exist.", 404)
        entity.delete()
        return Response.status(204).build()
    }

    @Provider
    class ErrorMapper : ExceptionMapper<Exception> {
        @Inject
        var objectMapper: ObjectMapper? = null
        override fun toResponse(exception: Exception): Response {
            LOGGER.error("Failed to handle request", exception)
            var code = 500
            if (exception is WebApplicationException) {
                code = exception.response.status
            }
            val exceptionJson = objectMapper!!.createObjectNode()
            exceptionJson.put("exceptionType", exception.javaClass.name)
            exceptionJson.put("code", code)
            if (exception.message != null) {
                exceptionJson.put("error", exception.message)
            }
            return Response.status(code)
                .entity(exceptionJson)
                .build()
        }
    }

    companion object {
        private val LOGGER = Logger.getLogger(CarBrandResource::class.java.name)
    }
}