package com.leg3nd.api.controller

import com.leg3nd.api.dto.NewUserRequestDto
import com.leg3nd.api.mapper.UserMapper
import com.leg3nd.domain.ports.application.UserApplicationPort
import org.koin.core.annotation.Single

@Single
class UserController(
    private val userApplicationPort: UserApplicationPort,
) {
    suspend fun signUp(universalId: String, newUserRequestDto: NewUserRequestDto) {
        userApplicationPort.signUp(UserMapper.mapNewUserRequestToDomainDto(universalId, newUserRequestDto))
    }
}
