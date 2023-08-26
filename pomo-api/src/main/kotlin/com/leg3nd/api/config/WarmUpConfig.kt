package com.leg3nd.api.config

import com.leg3nd.api.controller.TimerController
import io.ktor.server.application.*
import org.koin.ktor.ext.inject

fun Application.warmUp() {
    val timerController by inject<TimerController>()

    println(timerController)
}
