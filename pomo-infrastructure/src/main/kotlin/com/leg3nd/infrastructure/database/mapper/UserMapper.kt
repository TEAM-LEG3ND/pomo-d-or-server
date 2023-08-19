package com.leg3nd.infrastructure.database.mapper

import com.leg3nd.domain.core.model.User
import com.leg3nd.infrastructure.database.entity.UserEntity
import com.leg3nd.infrastructure.database.table.Users
import org.jetbrains.exposed.dao.id.EntityID
import java.time.OffsetDateTime
import java.time.ZoneId

object UserMapper {
    fun mapToDomain(entity: UserEntity): User = with(entity) {
        User(
            id = id.value,
            universalId = universalId,
            nickname = nickname,
            createdAt = OffsetDateTime.ofInstant(createdAt, ZoneId.systemDefault()),
            updatedAt = OffsetDateTime.ofInstant(updatedAt, ZoneId.systemDefault()),
        )
    }

    fun mapToEntity(domain: User): UserEntity = with(domain) {
        UserEntity(
            id = EntityID(id, Users),
        )
    }
}
