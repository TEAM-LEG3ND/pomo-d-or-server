package com.leg3nd.api.mapper

import com.leg3nd.api.dto.UserResponseDto
import com.leg3nd.domain.core.model.User

object UserMapper {
    fun mapToResponseDto(domain: User): UserResponseDto = with(domain) {
        UserResponseDto(nickname)
    }
}
