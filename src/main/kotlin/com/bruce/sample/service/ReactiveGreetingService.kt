package com.bruce.sample.service

import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.Uni

import javax.enterprise.context.ApplicationScoped
import java.time.Duration

@ApplicationScoped
class ReactiveGreetingService {

    fun greeting(name: String): Uni<String> {
        return Uni.createFrom().item(name)
                .onItem().transform { n -> String.format("hello %s", n) }
    }
}