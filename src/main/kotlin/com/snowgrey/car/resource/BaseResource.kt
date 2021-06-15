package com.snowgrey.car.resource

import com.snowgrey.car.entity.CarBrand
import com.snowgrey.car.service.BaseService
import com.snowgrey.car.service.CarBrandService
import org.jboss.resteasy.annotations.jaxrs.PathParam
import javax.inject.Inject
import javax.transaction.Transactional
import javax.ws.rs.*
import javax.ws.rs.core.Response

class BaseResource {
    lateinit var service: BaseService

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
}