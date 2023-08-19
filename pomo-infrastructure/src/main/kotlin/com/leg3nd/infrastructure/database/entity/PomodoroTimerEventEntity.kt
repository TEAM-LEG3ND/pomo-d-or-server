package com.leg3nd.infrastructure.database.entity

import com.leg3nd.infrastructure.database.table.PomodoroTimerEvents
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class PomodoroTimerEventEntity(id: EntityID<Long>) : BaseEntity(id, PomodoroTimerEvents) {
    companion object : LongEntityClass<PomodoroTimerEventEntity>(PomodoroTimerEvents)

    var author by PomodoroTimerEvents.author
    var timer by PomodoroTimerEvents.timer
    var type by PomodoroTimerEvents.type
}
