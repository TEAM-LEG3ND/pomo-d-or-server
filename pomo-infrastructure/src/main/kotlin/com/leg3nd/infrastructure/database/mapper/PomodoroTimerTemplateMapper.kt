package com.leg3nd.infrastructure.database.mapper

import com.leg3nd.domain.core.model.PomodoroTimerTemplate
import com.leg3nd.infrastructure.database.entity.PomodoroTimerTemplateEntity
import com.leg3nd.infrastructure.database.table.PomodoroTimerTemplates
import org.jetbrains.exposed.dao.id.EntityID
import java.time.OffsetDateTime
import java.time.ZoneId

object PomodoroTimerTemplateMapper {
    fun mapToDomain(entity: PomodoroTimerTemplateEntity): PomodoroTimerTemplate =
        with(entity) {
            PomodoroTimerTemplate(
                id = id.value,
                type = type,
                author = UserMapper.mapToDomain(author),
                title = title,
                workDuration = workDuration,
                breakDuration = breakDuration,
                repeatCount = repeatCount,
                createdAt = OffsetDateTime.ofInstant(createdAt, ZoneId.systemDefault()),
                updatedAt = OffsetDateTime.ofInstant(updatedAt, ZoneId.systemDefault()),
            )
        }

    fun mapToEntity(domain: PomodoroTimerTemplate): PomodoroTimerTemplateEntity =
        with(domain) {
            PomodoroTimerTemplateEntity(
                id = EntityID(id, PomodoroTimerTemplates),
            )
        }
}
