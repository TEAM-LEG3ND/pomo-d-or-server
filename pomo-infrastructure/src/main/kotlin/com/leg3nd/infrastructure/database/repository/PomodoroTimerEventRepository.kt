package com.leg3nd.infrastructure.database.repository

import com.leg3nd.domain.core.model.PomodoroTimer
import com.leg3nd.domain.core.model.PomodoroTimerEvent
import com.leg3nd.domain.ports.database.PomodoroTimerEventDatabasePort
import com.leg3nd.infrastructure.database.entity.PomodoroTimerEventEntity
import com.leg3nd.infrastructure.database.mapper.PomodoroTimerEventMapper
import com.leg3nd.infrastructure.database.mapper.PomodoroTimerMapper
import com.leg3nd.infrastructure.database.mapper.UserMapper
import com.leg3nd.infrastructure.database.table.PomodoroTimerEvents
import org.koin.core.annotation.Single

@Single
class PomodoroTimerEventRepository : PomodoroTimerEventDatabasePort {
    override fun create(newPomodoroTimerEvent: PomodoroTimerEvent): PomodoroTimerEvent {
        val createdEvent = PomodoroTimerEventEntity.new {
            author = UserMapper.mapToEntity(newPomodoroTimerEvent.author)
            timer = PomodoroTimerMapper.mapToEntity(newPomodoroTimerEvent.timer)
            type = newPomodoroTimerEvent.type
        }

        return PomodoroTimerEventMapper.mapToDomain(createdEvent)
    }

    override fun findByTimer(timer: PomodoroTimer): List<PomodoroTimerEvent> {
        val foundEvents = PomodoroTimerEventEntity.find {
            PomodoroTimerEvents.timer eq PomodoroTimerMapper.mapToEntity(timer).id
        }

        return foundEvents.map { PomodoroTimerEventMapper.mapToDomain(it) }
    }
}
