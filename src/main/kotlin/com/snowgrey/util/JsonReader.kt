package com.snowgrey.util

import io.vertx.core.file.FileSystem
import io.vertx.core.Vertx

class JsonReader {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val vertx = Vertx.vertx()
            val fs: FileSystem = vertx.fileSystem()
            fs.readFile("src/main/kotlin/demo.json") { result ->
                if (result.succeeded()) {
                    val json = result.result()

                } else {
                    System.err.println("Oh oh ..." + result.cause())
                }
            }
        }
    }

}