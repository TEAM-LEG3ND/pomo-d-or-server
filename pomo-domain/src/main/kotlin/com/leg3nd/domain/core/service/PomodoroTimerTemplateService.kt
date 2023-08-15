package com.leg3nd.domain.core.service

import com.leg3nd.domain.core.model.PomodoroTimerTemplate
import com.leg3nd.domain.core.model.User
import com.leg3nd.domain.ports.database.PomodoroTimerTemplateDatabasePort

class PomodoroTimerTemplateService(
    private val pomodoroTimerTemplateDatabasePort: PomodoroTimerTemplateDatabasePort,
) {
    fun create(newPomodoroTimerTemplate: PomodoroTimerTemplate): PomodoroTimerTemplate {
        return pomodoroTimerTemplateDatabasePort.create(newPomodoroTimerTemplate)
    }

    fun findById(id: Long): PomodoroTimerTemplate {
        return pomodoroTimerTemplateDatabasePort.findById(id)
    }

    fun findByAuthor(author: User): List<PomodoroTimerTemplate> {
        return pomodoroTimerTemplateDatabasePort.findByAuthor(author)
    }

    fun findByAuthorAndType(author: User, type: PomodoroTimerTemplate.Type): List<PomodoroTimerTemplate> {
        return pomodoroTimerTemplateDatabasePort.findByAuthorAndType(author, type)
    }
}
