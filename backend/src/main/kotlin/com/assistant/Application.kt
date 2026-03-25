package com.assistant

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "localhost", module = Application::module).start(wait = true)

}

fun Application.module() {
    configureRouting()
}

fun Application.configureRouting() {
    routing {
        get("/hello") {
            call.respond("Cus more!")
        }
    }
}