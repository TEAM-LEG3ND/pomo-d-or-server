package com.leg3nd.infrastructure.database.entity

import com.leg3nd.infrastructure.database.table.PomodoroTimers
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class PomodoroTimerEntity(id: EntityID<Long>) : BaseEntity(id, PomodoroTimers) {
    companion object : LongEntityClass<PomodoroTimerEntity>(PomodoroTimers)

    var author by UserEntity referencedOn PomodoroTimers.author
    var template by PomodoroTimerTemplateEntity referencedOn PomodoroTimers.template
    var status by PomodoroTimers.status
}
