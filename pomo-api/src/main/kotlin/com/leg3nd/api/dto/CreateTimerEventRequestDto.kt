package com.leg3nd.api.dto

import com.leg3nd.domain.core.model.PomodoroTimerEvent

data class CreateTimerEventRequestDto(
    val universalId: String,
    val timerId: Long,
    val eventType: PomodoroTimerEvent.Type,
)
