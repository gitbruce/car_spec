package com.snowgrey.car.verticle

import com.snowgrey.car.pojo.CarBrandPojo
import com.snowgrey.car.resource.CarBrandResource
import io.smallrye.mutiny.Uni
import io.smallrye.mutiny.vertx.core.AbstractVerticle
import io.vertx.ext.web.client.WebClientOptions
import io.vertx.mutiny.ext.web.client.WebClient
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class StoreCarBrand : AbstractVerticle() {

    override fun start() {
        vertx.eventBus().consumer<String>(CarBrandPojo::class.qualifiedName) { msg ->
            println("received message:" + msg.body())
            val client = WebClient.create(vertx)
            client.post()
        }
    }
}