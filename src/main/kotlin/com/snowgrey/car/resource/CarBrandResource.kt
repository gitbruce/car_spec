package com.snowgrey.car.resource

import com.snowgrey.car.entity.CarBrand
import com.snowgrey.util.SnowFlake
import com.fasterxml.jackson.databind.ObjectMapper
import com.snowgrey.car.service.BaseService
import com.snowgrey.car.service.CarBrandService
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

@Path("brands")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
class CarBrandResource : BaseResource<CarBrand>() {

    @Inject
    lateinit var service : CarBrandService

    override fun getService(): BaseService<CarBrand> {
        return service
    }

    override fun getLogger(): Logger {
        return Logger.getLogger(CarBrandResource::class.java.name)
    }
}