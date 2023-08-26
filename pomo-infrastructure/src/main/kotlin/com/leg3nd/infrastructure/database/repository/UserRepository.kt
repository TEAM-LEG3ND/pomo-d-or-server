package com.leg3nd.infrastructure.database.repository

import com.leg3nd.domain.core.model.User
import com.leg3nd.domain.ports.database.UserDatabasePort
import com.leg3nd.infrastructure.database.entity.UserEntity
import com.leg3nd.infrastructure.database.mapper.UserMapper
import com.leg3nd.infrastructure.database.table.Users
import org.koin.core.annotation.Single

@Single
class UserRepository : UserDatabasePort {
    override fun create(universalId: String, nickname: String): User {
        val createdUser = UserEntity.new {
            this.universalId = universalId
            this.nickname = nickname
        }

        return UserMapper.mapToDomain(createdUser)
    }

    override fun findById(id: Long): User {
        // TODO: add domain exception and exception mapper
        val foundUser = UserEntity.findById(id) ?: throw Exception("[findById] user with id: $id not found")

        return UserMapper.mapToDomain(foundUser)
    }

    override fun findByUniversalId(universalId: String): User {
        val foundUser = UserEntity.find {
            Users.universalId eq universalId
        }.firstOrNull()
            ?: throw Exception("[findByUniversalId] user with universalId: $universalId not found")

        return UserMapper.mapToDomain(foundUser)
    }
}
