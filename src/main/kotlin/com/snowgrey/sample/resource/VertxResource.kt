package com.snowgrey.sample.resource
import com.snowgrey.car.pojo.CarBrandPojo
import com.snowgrey.car.scrapy.ScrapyCarBrand
import com.snowgrey.car.verticle.StoreCarBrand
import io.quarkus.vertx.web.Route
import io.vertx.core.DeploymentOptions
import io.vertx.mutiny.core.Vertx
import io.vertx.core.http.HttpMethod
import io.vertx.ext.web.RoutingContext
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class VertxResource {
    @Inject
    lateinit var vertx: Vertx
    @Inject
    lateinit var scb: ScrapyCarBrand

    @Route(path = "/send", methods = [HttpMethod.GET])
    fun send(rc: RoutingContext) {
        println(vertx)
        scb.parseJson(CarBrandPojo::class)
        rc.response().end("send")
    }

    @Route(path = "/receive", methods = [HttpMethod.GET])
    fun receive(rc: RoutingContext) {
        vertx.deployVerticle(StoreCarBrand::class.java.name, DeploymentOptions().setInstances(2))
        rc.response().end("receive")
    }
}