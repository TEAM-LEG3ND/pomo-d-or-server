package com.leg3nd.infrastructure.database.entity

import com.leg3nd.infrastructure.database.table.Users
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserEntity(id: EntityID<Long>) : BaseEntity(id, Users) {
    companion object : LongEntityClass<UserEntity>(Users)

    var universalId by Users.universalId
    var nickname by Users.nickname
}
