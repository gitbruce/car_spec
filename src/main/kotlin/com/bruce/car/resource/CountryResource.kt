package com.bruce.car.resource

import com.bruce.car.entity.Country
import com.bruce.util.SnowFlake
import com.fasterxml.jackson.databind.ObjectMapper
import io.quarkus.panache.common.Sort
import org.jboss.logging.Logger
import org.jboss.resteasy.annotations.jaxrs.PathParam
import java.lang.Exception
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.transaction.Transactional
import javax.ws.rs.*
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Path("countries")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
class CountryResource {
    @GET
    fun get(): List<Country> {
        return Country.listAll(Sort.by("name"))
    }

    @GET
    @Path("{id}")
    fun getSingle(@PathParam id: Long): Country {
        return Country.findById(id) ?: throw WebApplicationException("country with id of $id does not exist.", 404)
    }

    @POST
    @Transactional
    fun create(country: Country): Response {
        if (country.id == null) {
            country.id = SnowFlake().nextId()
        }
//        country.createDate = Date()
//        country.lastUpdate = Date()
        country.persist()
        return Response.ok(country).status(201).build()
    }

    @PUT
    @Path("{id}")
    @Transactional
    fun update(@PathParam id: Long, country: Country): Country {
        if (country.name == "") {
            throw WebApplicationException("country Name was not set on request.", 422)
        }
        val entity: Country =
            Country.findById(id) ?: throw WebApplicationException("country with id of $id does not exist.", 404)
        entity.name = country.name
//        entity.lastUpdate = Date()
        return entity
    }

    @DELETE
    @Path("{id}")
    @Transactional
    fun delete(@PathParam id: Long): Response {
        val entity: Country =
            Country.findById(id) ?: throw WebApplicationException("country with id of $id does not exist.", 404)
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
        private val LOGGER = Logger.getLogger(CountryResource::class.java.name)
    }
}