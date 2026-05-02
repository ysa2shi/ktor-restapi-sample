package com.example.api

import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

fun Route.sampleRoutes() {
    get("/") {
        call.respondText("Hello, World!")
    }
}