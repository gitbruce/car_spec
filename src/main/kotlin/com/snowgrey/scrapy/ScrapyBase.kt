package com.snowgrey.scrapy

import com.snowgrey.base.BaseEntity
import io.smallrye.mutiny.Uni
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.client.WebClientOptions
import io.vertx.mutiny.core.Vertx
import io.vertx.mutiny.core.buffer.Buffer
import io.vertx.mutiny.ext.web.client.HttpResponse
import javax.inject.Inject
import io.vertx.mutiny.ext.web.client.WebClient
import org.jboss.resteasy.annotations.jaxrs.PathParam
import javax.annotation.PostConstruct

abstract class ScrapyBase {
    @Inject
    lateinit var vertx: Vertx

    lateinit var client: WebClient

    abstract fun getUrl(): String

    @PostConstruct
    open fun initialize() {
        client = WebClient.create(
            vertx,
            WebClientOptions().setDefaultHost("www.autohome.com.cn").setDefaultPort(443).setSsl(true)
                .setTrustAll(true)
        )
    }

    open fun retrieveJson(): Uni<JsonObject>? {
        return client[getUrl()]
            .send()
            .map { resp: HttpResponse<Buffer?> ->
                if (resp.statusCode() == 200) {
                    return@map resp.bodyAsJsonObject()
                } else {
                    return@map JsonObject()
                        .put("code", resp.statusCode())
                        .put("message", resp.bodyAsString())
                }
            }
    }

    abstract fun tranverseEntity(): List<BaseEntity>
}