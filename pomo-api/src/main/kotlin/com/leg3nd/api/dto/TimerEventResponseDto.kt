package com.leg3nd.api.dto

import com.leg3nd.domain.core.model.PomodoroTimerEvent

data class TimerEventResponseDto(
    val type: PomodoroTimerEvent.Type,
    val createdAt: Long,
)
