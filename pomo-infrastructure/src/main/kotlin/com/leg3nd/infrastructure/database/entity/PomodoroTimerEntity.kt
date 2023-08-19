package com.leg3nd.infrastructure.database.entity

import com.leg3nd.infrastructure.database.table.PomodoroTimers
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class PomodoroTimerEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<PomodoroTimerEntity>(PomodoroTimers)

    val author by PomodoroTimers.author
    val template by PomodoroTimers.template
    val status by PomodoroTimers.status
    val createdAt by PomodoroTimers.createdAt
    val updatedAt by PomodoroTimers.updatedAt
}
