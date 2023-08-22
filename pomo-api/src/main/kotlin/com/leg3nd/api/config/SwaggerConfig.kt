package com.leg3nd.api.config

import io.github.smiley4.ktorswaggerui.SwaggerUI
import io.github.smiley4.ktorswaggerui.dsl.SwaggerUiSort
import io.github.smiley4.ktorswaggerui.dsl.SwaggerUiSyntaxHighlight
import io.ktor.server.application.*

fun Application.configureSwagger() {
    install(SwaggerUI) {
        swagger {
            rootHostPath = "/pomodor"
            forwardRoot = false
            swaggerUrl = "swagger-ui"
            onlineSpecValidator()
            displayOperationId = false
            showTagFilterInput = true
            sort = SwaggerUiSort.HTTP_METHOD
            syntaxHighlight = SwaggerUiSyntaxHighlight.MONOKAI
        }
        server {
            url = "https://api.server.d0lim.com/pomodor/"
            description = "Dev(mac mini)"
        }
        server {
            url = "http://localhost:8080"
            description = "Local"
        }
    }
}
