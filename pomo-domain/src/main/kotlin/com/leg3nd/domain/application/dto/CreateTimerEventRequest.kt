package com.leg3nd.domain.application.dto

import com.leg3nd.domain.core.model.PomodoroTimerEvent

data class CreateTimerEventRequest(
    val universalId: String,
    val timerId: Long,
    val eventType: PomodoroTimerEvent.Type,
)
