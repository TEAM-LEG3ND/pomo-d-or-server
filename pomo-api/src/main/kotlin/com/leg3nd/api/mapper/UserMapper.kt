package com.leg3nd.api.mapper

import com.leg3nd.api.dto.NewUserRequestDto
import com.leg3nd.api.dto.UserResponseDto
import com.leg3nd.domain.application.dto.CreateUserRequest
import com.leg3nd.domain.core.model.User

object UserMapper {
    fun mapNewUserRequestToDomainDto(universalId: String, newUserRequestDto: NewUserRequestDto): CreateUserRequest =
        with(newUserRequestDto) {
            CreateUserRequest(universalId, nickname)
        }

    fun mapToResponseDto(domain: User): UserResponseDto = with(domain) {
        UserResponseDto(nickname)
    }
}
