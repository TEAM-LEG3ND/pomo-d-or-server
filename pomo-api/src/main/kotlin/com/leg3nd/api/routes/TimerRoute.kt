package com.leg3nd.api.routes

import com.leg3nd.api.controller.TimerController
import com.leg3nd.api.dto.CreateTimerEventRequestDto
import com.leg3nd.api.dto.StartAnonymousTimerRequestDto
import com.leg3nd.api.dto.StartSignedTimerRequestDto
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Routing.timerRoute() {
    val timerController by inject<TimerController>()

    route("/api/v1/timer") {
        get("/{id}") {
            val universalId =
                call.request.headers["x-account-id"] ?: throw BadRequestException("No x-account-id provided")
            val timerId = call.parameters["id"]?.toLong() ?: throw BadRequestException("No timer id provided")

            val response = timerController.getTimerSnapshotById(timerId)

            call.respond(response)
        }

        get("/last") {
            val universalId =
                call.request.headers["x-account-id"] ?: throw BadRequestException("No x-account-id provided")

            val response = timerController.getLastStartedTimerSnapshot(universalId)

            call.respond(response)
        }

        post("/anonymous") {
            val startAnonymousTimerRequestDto = call.receive<StartAnonymousTimerRequestDto>()

            val response = timerController.startAnonymousTimer(startAnonymousTimerRequestDto)

            call.respond(HttpStatusCode.Created, response)
        }

        post("/signed") {
            val startSignedTimerRequestDto = call.receive<StartSignedTimerRequestDto>()

            val response = timerController.startSignedTimer(startSignedTimerRequestDto)

            call.respond(HttpStatusCode.Created, response)
        }

        post("/event") {
            val createTimerRequestDto = call.receive<CreateTimerEventRequestDto>()

            val response = timerController.createTimerEvent(createTimerRequestDto)

            call.respond(HttpStatusCode.Created, response)
        }
    }
}
