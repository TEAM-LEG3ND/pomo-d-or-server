package com.leg3nd.infrastructure.database.mapper

import com.leg3nd.domain.core.model.PomodoroTimerEvent
import com.leg3nd.infrastructure.database.entity.PomodoroTimerEventEntity
import com.leg3nd.infrastructure.database.table.PomodoroTimerEvents
import org.jetbrains.exposed.dao.id.EntityID
import java.time.OffsetDateTime
import java.time.ZoneId

object PomodoroTimerEventMapper {
    fun mapToDomain(entity: PomodoroTimerEventEntity): PomodoroTimerEvent =
        with(entity) {
            PomodoroTimerEvent(
                id = id.value,
                author = UserMapper.mapToDomain(author),
                timer = PomodoroTimerMapper.mapToDomain(timer),
                type = type,
                createdAt = OffsetDateTime.ofInstant(createdAt, ZoneId.systemDefault()),
                updatedAt = OffsetDateTime.ofInstant(updatedAt, ZoneId.systemDefault()),
            )
        }

    fun mapToEntity(domain: PomodoroTimerEvent): PomodoroTimerEventEntity =
        with(domain) {
            PomodoroTimerEventEntity(id = EntityID(id, PomodoroTimerEvents))
        }
}
