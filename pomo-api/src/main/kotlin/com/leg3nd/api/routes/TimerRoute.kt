package com.leg3nd.api.routes

import com.leg3nd.api.controller.TimerController
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Routing.timerRoute() {
    val timerController by inject<TimerController>()

    println(timerController)

    route("/api/v1/timer") {
    }
}
