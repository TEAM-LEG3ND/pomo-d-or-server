package com.leg3nd.domain.ports.application

import com.leg3nd.domain.application.dto.CreateUserRequest

interface UserApplicationPort {
    suspend fun signUp(newUser: CreateUserRequest)
}
