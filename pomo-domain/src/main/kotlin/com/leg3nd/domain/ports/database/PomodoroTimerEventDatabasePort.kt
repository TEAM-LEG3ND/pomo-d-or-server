package com.leg3nd.domain.ports.database

import com.leg3nd.domain.core.model.PomodoroTimer
import com.leg3nd.domain.core.model.PomodoroTimerEvent

interface PomodoroTimerEventDatabasePort {
    fun create(newPomodoroTimerEvent: PomodoroTimerEvent): PomodoroTimerEvent

    fun findByTimer(timer: PomodoroTimer): List<PomodoroTimerEvent>
}
