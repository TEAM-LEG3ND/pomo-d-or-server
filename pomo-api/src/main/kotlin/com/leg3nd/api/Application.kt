package com.leg3nd.api

import com.leg3nd.api.config.configureKoin
import com.leg3nd.api.config.configureRoute
import com.leg3nd.api.config.configureSerialization
import com.leg3nd.api.config.configureSwagger
import com.leg3nd.api.config.warmUp
import io.ktor.server.application.*
import io.ktor.server.netty.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    configureSerialization()
    configureSwagger()
    configureKoin()
    configureRoute()
    warmUp()
}
