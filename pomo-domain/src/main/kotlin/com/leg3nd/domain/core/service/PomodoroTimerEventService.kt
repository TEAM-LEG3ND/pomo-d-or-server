package com.leg3nd.domain.core.service

import com.leg3nd.domain.core.model.PomodoroTimer
import com.leg3nd.domain.core.model.PomodoroTimerEvent
import com.leg3nd.domain.ports.database.PomodoroTimerEventDatabasePort

class PomodoroTimerEventService(
    private val pomodoroTimerEventDatabasePort: PomodoroTimerEventDatabasePort,
) {
    fun create(newPomodoroTimerEvent: PomodoroTimerEvent): PomodoroTimerEvent {
        return pomodoroTimerEventDatabasePort.create(newPomodoroTimerEvent)
    }

    fun findByTimer(timer: PomodoroTimer): List<PomodoroTimerEvent> {
        return pomodoroTimerEventDatabasePort.findByTimer(timer)
    }
}
