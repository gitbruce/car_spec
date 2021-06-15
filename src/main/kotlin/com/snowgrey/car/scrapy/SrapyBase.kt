package com.snowgrey.car.scrapy

import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.client.WebClientOptions
import io.vertx.mutiny.core.Vertx
import io.vertx.mutiny.core.buffer.Buffer
import io.vertx.mutiny.core.file.FileSystem
import io.vertx.mutiny.ext.web.client.HttpResponse
import io.vertx.mutiny.ext.web.client.WebClient
import javax.annotation.PostConstruct
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class SrapyBase<T> {
    @Inject
    lateinit var vertx: Vertx
    lateinit var client: WebClient

    abstract fun getStructure(): String

    @PostConstruct
    open fun initialize() {
        client = WebClient.create(
            vertx,
            WebClientOptions().setDefaultHost("www.autohome.com.cn").setDefaultPort(443).setSsl(true)
                .setTrustAll(true)
        )
    }

    //    inline fun <reified T: Any> retrieveJson() {
    fun <T : Any> parseJson(c: KClass<T>) {
        var json = ""
        if (getMode() == "URL") {
            client[getPath()].send().map { resp: HttpResponse<Buffer> ->
                if (resp.statusCode() == 200) {
                    json = resp.bodyAsString()
                }
            }
        } else {
            val fs: FileSystem = vertx.fileSystem()
            json = fs.readFileBlocking(getPath()).toString()
        }
        var jo = JsonObject(json)
        val status = jo.getValue("returncode")
        var jarray : JsonArray? = null
        if (status == 0) {
            val structure = getStructure().split(".")
            for ((index, value) in structure.withIndex()) {
                if (index == structure.size-1) {
                    jarray = jo.getJsonArray(value)
                } else {
                    jo = jo.getJsonObject(value)
                }
            }
            for (item in jarray!!) {
//                val brand = JsonReader.mapper.readValue(item.toString(), c.java)
                vertx.eventBus().sendAndForget(c.qualifiedName, item.toString())
//                println("eb sending "+item.toString())
            }
        }
    }

    abstract fun getPath(): String

    abstract fun getMode(): String
}