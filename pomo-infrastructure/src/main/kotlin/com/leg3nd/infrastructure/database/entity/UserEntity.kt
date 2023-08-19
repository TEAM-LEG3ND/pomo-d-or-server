package com.leg3nd.infrastructure.database.entity

import com.leg3nd.infrastructure.database.table.Users
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<UserEntity>(Users)

    val universalId by Users.universalId
    val nickname by Users.nickname
    val createdAt by Users.createdAt
    val updatedAt by Users.updatedAt
}
