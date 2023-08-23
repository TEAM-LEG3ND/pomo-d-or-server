package com.leg3nd.api.dto

import com.leg3nd.domain.core.model.PomodoroTimerTemplate

data class TimerTemplateResponseDto(
    val id: Long,
    val type: PomodoroTimerTemplate.Type,
    val author: UserResponseDto,
    val title: String,
    val workDuration: Long,
    val breakDuration: Long,
    val repeatCount: Long,
)
