package com.leg3nd.domain.ports.database

import com.leg3nd.domain.core.model.User

interface UserDatabasePort {
    fun create(universalId: String, nickname: String): User

    fun findById(id: Long): User

    fun findByUniversalId(universalId: String): User
}
