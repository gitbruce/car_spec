package com.bruce.sample.resource

import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

import com.bruce.sample.service.ReactiveGreetingService
import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.Uni
import org.reactivestreams.Publisher

@Path("/hello")
class ReactiveGreetingResource {

    @Inject
    internal var service: ReactiveGreetingService? = null

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/greeting/{name}")
    fun greeting(name: String): Uni<String> {
        return service!!.greeting(name)
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun hello(): String {
        return "hello"
    }
}