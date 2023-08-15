package com.leg3nd.domain.application.dto

import com.leg3nd.domain.core.model.PomodoroTimer
import com.leg3nd.domain.core.model.PomodoroTimerEvent

data class TimerSnapshotResponse(
    val timer: PomodoroTimer,
    val events: List<PomodoroTimerEvent>,
)
