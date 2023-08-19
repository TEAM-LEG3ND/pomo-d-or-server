package com.leg3nd.infrastructure.database.entity

import com.leg3nd.infrastructure.database.table.PomodoroTimerTemplates
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class PomodoroTimerTemplateEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<PomodoroTimerTemplateEntity>(PomodoroTimerTemplates)

    val type by PomodoroTimerTemplates.type
    val author by PomodoroTimerTemplates.author
    val title by PomodoroTimerTemplates.title
    val workDuration by PomodoroTimerTemplates.workDuration
    val breakDuration by PomodoroTimerTemplates.breakDuration
    val repeatCount by PomodoroTimerTemplates.repeatCount
    val createdAt by PomodoroTimerTemplates.createdAt
    val updatedAt by PomodoroTimerTemplates.updatedAt
}
