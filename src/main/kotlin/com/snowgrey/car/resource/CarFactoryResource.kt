package com.snowgrey.car.resource

import com.snowgrey.car.entity.CarFactory
import com.snowgrey.util.SnowFlake
import com.fasterxml.jackson.databind.ObjectMapper
import io.quarkus.panache.common.Sort
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

@Path("factories")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
class CarFactoryResource {
    @GET
    fun get(): List<CarFactory> {
        return CarFactory.listAll(Sort.by("name"))
    }

    @GET
    @Path("{id}")
    fun getSingle(@PathParam id: Long): CarFactory {
        return CarFactory.findById(id) ?: throw WebApplicationException("item with id of $id does not exist.", 404)
    }

    @POST
    @Transactional
    fun create(item: CarFactory): Response {
        if (item.id == null) {
            item.id = SnowFlake().nextId()
        }
        item.persist()
        return Response.ok(item).status(201).build()
    }

    @PUT
    @Path("{id}")
    @Transactional
    fun update(@PathParam id: Long, item: CarFactory): CarFactory {
        if (item.name == "") {
            throw WebApplicationException("item Name was not set on request.", 422)
        }
        val entity: CarFactory =
            CarFactory.findById(id) ?: throw WebApplicationException("item with id of $id does not exist.", 404)
        entity.name = item.name
        entity.brand?.id = item.brand?.id
        entity.brandName = item.brandName
        return entity
    }

    @DELETE
    @Path("{id}")
    @Transactional
    fun delete(@PathParam id: Long): Response {
        val entity: CarFactory =
            CarFactory.findById(id) ?: throw WebApplicationException("item with id of $id does not exist.", 404)
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
        private val LOGGER = Logger.getLogger(CarFactoryResource::class.java.name)
    }
}