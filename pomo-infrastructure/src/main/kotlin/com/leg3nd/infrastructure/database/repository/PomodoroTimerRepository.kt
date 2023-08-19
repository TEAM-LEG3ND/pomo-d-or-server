package com.leg3nd.infrastructure.database.repository

import com.leg3nd.domain.core.model.PomodoroTimer
import com.leg3nd.domain.core.model.User
import com.leg3nd.domain.ports.database.PomodoroTimerDatabasePort
import com.leg3nd.infrastructure.database.entity.PomodoroTimerEntity
import com.leg3nd.infrastructure.database.mapper.PomodoroTimerMapper
import com.leg3nd.infrastructure.database.mapper.PomodoroTimerTemplateMapper
import com.leg3nd.infrastructure.database.mapper.UserMapper
import com.leg3nd.infrastructure.database.table.PomodoroTimers
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.update

class PomodoroTimerRepository : PomodoroTimerDatabasePort {
    override fun create(newPomodoroTimer: PomodoroTimer): PomodoroTimer {
        val createdTimer = PomodoroTimerEntity.new {
            author = UserMapper.mapToEntity(newPomodoroTimer.author)
            template = PomodoroTimerTemplateMapper.mapToEntity(newPomodoroTimer.template)
            status = status
        }

        return PomodoroTimerMapper.mapToDomain(createdTimer)
    }

    override fun findById(id: Long): PomodoroTimer {
        val foundTimer = PomodoroTimerEntity.findById(id)
            ?: throw Exception("[findById] PomodoroTimer with id: $id not found")

        return PomodoroTimerMapper.mapToDomain(foundTimer)
    }

    override fun findByAuthorAndStatus(author: User, status: PomodoroTimer.Status): List<PomodoroTimer> {
        val foundTimers = PomodoroTimerEntity.find {
            PomodoroTimers.author eq UserMapper.mapToEntity(author).id
            PomodoroTimers.status eq status
        }

        return foundTimers.map { PomodoroTimerMapper.mapToDomain(it) }
    }

    override fun findOneByAuthorOrderByCreatedAtDesc(author: User): PomodoroTimer {
        val foundTimer = PomodoroTimerEntity
            .find {
                PomodoroTimers.author eq UserMapper.mapToEntity(author).id
            }
            .orderBy(PomodoroTimers.createdAt to SortOrder.DESC)
            .firstOrNull()
            ?: throw Exception(
                "[findOneByAuthorOrderByCreatedAtDesc] " +
                    "PomodoroTimer with authorId: ${author.id} not found",
            )

        return PomodoroTimerMapper.mapToDomain(foundTimer)
    }

    override fun updateStatusByIdIn(ids: List<Long>, status: PomodoroTimer.Status) {
        PomodoroTimers.update({ PomodoroTimers.id inList ids }) {
            it[PomodoroTimers.status] = status
        }
    }
}
