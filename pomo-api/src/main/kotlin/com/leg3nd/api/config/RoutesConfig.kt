package com.leg3nd.api.config

import com.leg3nd.api.routes.healthCheckRoute
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRoute() {
    routing {
        healthCheckRoute()
    }
}
