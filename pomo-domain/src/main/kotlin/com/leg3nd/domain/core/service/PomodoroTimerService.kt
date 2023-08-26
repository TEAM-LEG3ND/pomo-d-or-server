package com.leg3nd.domain.core.service

import com.leg3nd.domain.core.model.PomodoroTimer
import com.leg3nd.domain.core.model.User
import com.leg3nd.domain.ports.database.PomodoroTimerDatabasePort
import org.koin.core.annotation.Single

@Single
class PomodoroTimerService(
    private val pomodoroTimerDatabasePort: PomodoroTimerDatabasePort,
) {
    fun create(newPomodoroTimer: PomodoroTimer): PomodoroTimer {
        return pomodoroTimerDatabasePort.create(newPomodoroTimer)
    }

    fun findById(id: Long): PomodoroTimer {
        return pomodoroTimerDatabasePort.findById(id)
    }

    fun findByAuthorAndStatus(author: User, status: PomodoroTimer.Status): List<PomodoroTimer> {
        return pomodoroTimerDatabasePort.findByAuthorAndStatus(author, status)
    }

    fun findLastStartedByAuthor(author: User): PomodoroTimer {
        return pomodoroTimerDatabasePort.findOneByAuthorOrderByCreatedAtDesc(author)
    }

    fun updateStatusByIds(ids: List<Long>, status: PomodoroTimer.Status) {
        pomodoroTimerDatabasePort.updateStatusByIdIn(ids, status)
    }
}
