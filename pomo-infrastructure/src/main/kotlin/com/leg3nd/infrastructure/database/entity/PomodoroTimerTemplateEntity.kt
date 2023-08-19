package com.leg3nd.infrastructure.database.entity

import com.leg3nd.infrastructure.database.table.PomodoroTimerTemplates
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class PomodoroTimerTemplateEntity(id: EntityID<Long>) : BaseEntity(id, PomodoroTimerTemplates) {
    companion object : LongEntityClass<PomodoroTimerTemplateEntity>(PomodoroTimerTemplates)

    var type by PomodoroTimerTemplates.type
    var author by PomodoroTimerTemplates.author
    var title by PomodoroTimerTemplates.title
    var workDuration by PomodoroTimerTemplates.workDuration
    var breakDuration by PomodoroTimerTemplates.breakDuration
    var repeatCount by PomodoroTimerTemplates.repeatCount
}
