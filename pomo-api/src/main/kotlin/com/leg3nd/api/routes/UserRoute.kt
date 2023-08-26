package com.leg3nd.api.routes

import com.leg3nd.api.controller.UserController
import com.leg3nd.api.dto.NewUserRequestDto
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Routing.userRoute() {
    val userController by inject<UserController>()

    route("/api/v1/user") {
        post {
            val universalId =
                call.request.headers["x-account-id"] ?: throw BadRequestException("No x-account-id provided")
            val newUserRequestDto = call.receive<NewUserRequestDto>()

            userController.signUp(universalId, newUserRequestDto)

            call.respond(HttpStatusCode.Created)
        }
    }
}
