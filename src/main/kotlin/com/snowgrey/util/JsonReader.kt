package com.snowgrey.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.snowgrey.car.pojo.CarBrandPojo
import io.vertx.core.file.FileSystem
import io.vertx.core.Vertx
import io.vertx.core.json.JsonObject

class JsonReader {
    companion object {
        val mapper = ObjectMapper().registerModule(KotlinModule())

        @JvmStatic
        fun main(args: Array<String>) {
            val vertx = Vertx.vertx()
            val fs: FileSystem = vertx.fileSystem()
            fs.readFile("src/main/kotlin/demo.json") { result ->
                if (result.succeeded()) {
                    val json = result.result()
                    val jo = JsonObject(json)
                    val status = jo.getValue("returncode")
                    if (status == 0) {
                        val jarray = jo.getJsonObject("result").getJsonArray("branditems")
                        for (item in jarray) {
                            val brand = mapper.readValue<CarBrandPojo>(item.toString())
                            println(brand)
                        }
                    }
                } else {
                    System.err.println("Oh oh ..." + result.cause())
                }
            }
        }
    }

}