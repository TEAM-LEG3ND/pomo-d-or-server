package com.leg3nd.domain.ports.database

import com.leg3nd.domain.core.model.PomodoroTimerTemplate
import com.leg3nd.domain.core.model.User

interface PomodoroTimerTemplateDatabasePort {
    fun create(newPomodoroTimerTemplate: PomodoroTimerTemplate): PomodoroTimerTemplate

    fun findById(id: Long): PomodoroTimerTemplate

    fun findByAuthor(author: User): List<PomodoroTimerTemplate>

    fun findByAuthorAndType(author: User, type: PomodoroTimerTemplate.Type): List<PomodoroTimerTemplate>
}
