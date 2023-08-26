package com.leg3nd.domain.core.service

import com.leg3nd.domain.core.model.User
import com.leg3nd.domain.ports.database.UserDatabasePort
import org.koin.core.annotation.Single

@Single
class UserService(
    private val userDatabasePort: UserDatabasePort,
) {
    fun create(universalId: String, nickname: String): User {
        return userDatabasePort.create(universalId, nickname)
    }

    fun findById(id: Long): User {
        return userDatabasePort.findById(id)
    }

    fun findByUniversalId(universalId: String): User {
        return userDatabasePort.findByUniversalId(universalId)
    }
}
