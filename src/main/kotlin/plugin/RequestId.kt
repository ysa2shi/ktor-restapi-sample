package com.example.plugin

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.callid.CallId
import java.util.*

fun Application.configureRequestId() {

    install(CallId) {
        header("X-Request-ID")
        generate { UUID.randomUUID().toString() }
    }
}