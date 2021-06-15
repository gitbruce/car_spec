package com.snowgrey.car;

import com.snowgrey.car.verticle.StoreCarBrand;
import io.quarkus.runtime.annotations.QuarkusMain;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.vertx.core.DeploymentOptions
import io.vertx.mutiny.core.Vertx

import javax.inject.Inject;
import java.lang.Exception;

@QuarkusMain
class Main : QuarkusApplication {
    @Inject
    lateinit var vertx: Vertx

    override fun run(vararg args: String?): Int {
        System.out.println("Do startup logic here");
        vertx.deployVerticleAndForget(StoreCarBrand::class.java.name, DeploymentOptions().setInstances(2))
        Quarkus.waitForExit();
        return 0;
    }
}