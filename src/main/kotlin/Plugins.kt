package com.example

import com.example.plugin.configureCors
import com.example.plugin.configureExceptionHandling
import com.example.plugin.configureLogging
import com.example.plugin.configureRequestId
import com.example.plugin.configureSerialization
import io.ktor.server.application.Application

fun Application.configurePlugins() {
    configureCors()
    configureLogging()
    configureRequestId()
    configureSerialization()
    configureExceptionHandling()
}