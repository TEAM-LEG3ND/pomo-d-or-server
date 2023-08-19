package com.leg3nd.infrastructure.database.entity

import com.leg3nd.infrastructure.database.table.PomodoroTimerEvents
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class PomodoroTimerEventEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<PomodoroTimerEventEntity>(PomodoroTimerEvents)

    val author by PomodoroTimerEvents.author
    val timer by PomodoroTimerEvents.timer
    val type by PomodoroTimerEvents.type
    val createdAt by PomodoroTimerEvents.createdAt
    val updatedAt by PomodoroTimerEvents.updatedAt
}
