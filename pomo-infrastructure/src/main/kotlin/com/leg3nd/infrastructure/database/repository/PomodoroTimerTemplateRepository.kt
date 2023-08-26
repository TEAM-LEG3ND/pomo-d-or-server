package com.leg3nd.infrastructure.database.repository

import com.leg3nd.domain.core.model.PomodoroTimerTemplate
import com.leg3nd.domain.core.model.User
import com.leg3nd.domain.ports.database.PomodoroTimerTemplateDatabasePort
import com.leg3nd.infrastructure.database.entity.PomodoroTimerTemplateEntity
import com.leg3nd.infrastructure.database.mapper.PomodoroTimerTemplateMapper
import com.leg3nd.infrastructure.database.mapper.UserMapper
import com.leg3nd.infrastructure.database.table.PomodoroTimerTemplates
import org.koin.core.annotation.Single

@Single
class PomodoroTimerTemplateRepository : PomodoroTimerTemplateDatabasePort {
    override fun create(newPomodoroTimerTemplate: PomodoroTimerTemplate): PomodoroTimerTemplate {
        val createdTemplate = PomodoroTimerTemplateEntity.new {
            type = newPomodoroTimerTemplate.type
            author = UserMapper.mapToEntity(newPomodoroTimerTemplate.author)
            title = newPomodoroTimerTemplate.title
            workDuration = newPomodoroTimerTemplate.workDuration
            breakDuration = newPomodoroTimerTemplate.breakDuration
            repeatCount = newPomodoroTimerTemplate.repeatCount
        }

        return PomodoroTimerTemplateMapper.mapToDomain(createdTemplate)
    }

    override fun findById(id: Long): PomodoroTimerTemplate {
        val foundTemplate = PomodoroTimerTemplateEntity.findById(id)
            ?: throw Exception("[findById] PomodoroTimerTemplate with id: $id not found")

        return PomodoroTimerTemplateMapper.mapToDomain(foundTemplate)
    }

    override fun findByAuthor(author: User): List<PomodoroTimerTemplate> {
        val foundTemplates = PomodoroTimerTemplateEntity.find {
            PomodoroTimerTemplates.author eq UserMapper.mapToEntity(author).id
        }

        return foundTemplates.map { PomodoroTimerTemplateMapper.mapToDomain(it) }
    }

    override fun findByAuthorAndType(author: User, type: PomodoroTimerTemplate.Type): List<PomodoroTimerTemplate> {
        val foundTemplates = PomodoroTimerTemplateEntity.find {
            PomodoroTimerTemplates.author eq UserMapper.mapToEntity(author).id
            PomodoroTimerTemplates.type eq type
        }

        return foundTemplates.map { PomodoroTimerTemplateMapper.mapToDomain(it) }
    }
}
