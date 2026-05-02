package com.example.plugin

import com.example.api.ErrorResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.callid.callId
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond

fun Application.configureExceptionHandling() {

    install(StatusPages) {

        exception<IllegalArgumentException> { call, cause ->
            val requestId = call.callId ?: "no-id"

            call.application.environment.log.warn(
                "[${requestId}] Bad request: ${cause.message}"
            )
            call.respond(
                HttpStatusCode.BadRequest,
                ErrorResponse(
                    code = "BAD_REQUEST",
                    message = cause.message ?: "Invalid request"
                )
            )
        }

        /**
         * Unexpected Error（想定外）
         */
        exception<Throwable> { call, cause ->
            val requestId = call.callId ?: "no-id"
            call.application.environment.log.error(
                "[${requestId}] Unhandled exception",
                cause
            )
            call.respond(
                HttpStatusCode.InternalServerError,
                ErrorResponse(
                    code = "INTERNAL_SERVER_ERROR",
                    message = "Unexpected error"
                )
            )
        }
    }
}