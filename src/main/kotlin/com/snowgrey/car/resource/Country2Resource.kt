package com.snowgrey.car.resource

import com.snowgrey.car.entity.CarBrand
import com.snowgrey.car.entity.Country
import com.snowgrey.car.service.BaseService
import com.snowgrey.car.service.CarBrandService
import com.snowgrey.car.service.CountryService
import org.jboss.logging.Logger
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.ws.rs.Consumes
import javax.ws.rs.Path
import javax.ws.rs.Produces

@Path("countries2")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
class Country2Resource : BaseResource<Country>() {

    @Inject
    lateinit var service : CountryService

    override fun getService(): BaseService<Country> {
        println("country service is $service")
        return service
    }

    override fun getLogger(): Logger {
        return Logger.getLogger(Country2Resource::class.java.name)
    }
}