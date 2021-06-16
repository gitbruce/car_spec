package com.snowgrey.car.resource

import com.fasterxml.jackson.databind.ObjectMapper
import com.snowgrey.car.service.BaseService
import org.jboss.logging.Logger
import org.jboss.resteasy.annotations.jaxrs.PathParam
import java.lang.Exception
import javax.inject.Inject
import javax.transaction.Transactional
import javax.ws.rs.*
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

abstract class BaseResource<T> {

    abstract fun getService(): BaseService<T>
    abstract fun getLogger(): Logger

    @GET
    fun getAll(): List<T> {
        println("come to list")
        return getService().getAll()
    }

    @GET
    @Path("{id}")
    fun getSingle(@PathParam id: Long): T {
        return getService().getSingle(id) ?: throw WebApplicationException("brand with id of $id does not exist.", 404)
    }

    @POST
    @Transactional
    fun create(item: T): Response {
        getService().create(item)
        return Response.ok(item).status(201).build()
    }

    @PUT
    @Path("{id}")
    @Transactional
    fun update(@PathParam id: Long, item: T): T? {
        return getService().update(id, item)
    }

    @DELETE
    @Path("{id}")
    @Transactional
    fun delete(@PathParam id: Long): Response {
        getService().delete(id)
        return Response.status(204).build()
    }


    @Provider
    class ErrorMapper : ExceptionMapper<Exception> {
        @Inject
        var objectMapper: ObjectMapper? = null
        override fun toResponse(exception: Exception): Response {
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

}