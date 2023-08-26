package com.leg3nd.api.config

import com.leg3nd.api.controller.TimerController
import com.leg3nd.api.controller.UserController
import io.ktor.server.application.*
import org.koin.ktor.ext.inject
import org.slf4j.LoggerFactory

fun Application.warmUp() {
    val log = LoggerFactory.getLogger("WarmUp")

    val timerController by inject<TimerController>()
    val userController by inject<UserController>()

    log.info(timerController.toString())
    log.info(userController.toString())
}
