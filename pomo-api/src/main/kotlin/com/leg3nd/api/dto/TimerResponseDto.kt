package com.leg3nd.api.dto

import com.leg3nd.domain.core.model.PomodoroTimer

data class TimerResponseDto(
    val id: Long,
    val author: UserResponseDto,
    val template: TimerTemplateResponseDto,
    val status: PomodoroTimer.Status,
    val createdAt: Long,
    val updatedAt: Long,
)
