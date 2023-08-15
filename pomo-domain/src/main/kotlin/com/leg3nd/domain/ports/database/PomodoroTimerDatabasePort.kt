package com.leg3nd.domain.ports.database

import com.leg3nd.domain.core.model.PomodoroTimer
import com.leg3nd.domain.core.model.User

interface PomodoroTimerDatabasePort {
    fun create(newPomodoroTimer: PomodoroTimer): PomodoroTimer

    fun findById(id: Long): PomodoroTimer

    fun findByAuthorAndStatus(author: User, status: PomodoroTimer.Status): List<PomodoroTimer>

    fun findOneByAuthorOrderByCreatedAtDesc(author: User): PomodoroTimer

    fun updateStatusByIdIn(ids: List<Long>, status: PomodoroTimer.Status)
}
