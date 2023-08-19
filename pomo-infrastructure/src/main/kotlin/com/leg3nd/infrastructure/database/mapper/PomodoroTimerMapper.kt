package com.leg3nd.infrastructure.database.mapper

import com.leg3nd.domain.core.model.PomodoroTimer
import com.leg3nd.infrastructure.database.entity.PomodoroTimerEntity
import com.leg3nd.infrastructure.database.table.PomodoroTimers
import org.jetbrains.exposed.dao.id.EntityID
import java.time.OffsetDateTime
import java.time.ZoneId

object PomodoroTimerMapper {
    fun mapToDomain(entity: PomodoroTimerEntity): PomodoroTimer =
        with(entity) {
            PomodoroTimer(
                id = id.value,
                author = UserMapper.mapToDomain(author),
                template = PomodoroTimerTemplateMapper.mapToDomain(template),
                status = status,
                createdAt = OffsetDateTime.ofInstant(createdAt, ZoneId.systemDefault()),
                updatedAt = OffsetDateTime.ofInstant(updatedAt, ZoneId.systemDefault()),
            )
        }

    fun mapToEntity(domain: PomodoroTimer): PomodoroTimerEntity =
        with(domain) {
            PomodoroTimerEntity(EntityID(id, PomodoroTimers))
        }
}
