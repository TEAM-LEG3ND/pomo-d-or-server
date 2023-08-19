package com.leg3nd.infrastructure.database.entity

import com.leg3nd.infrastructure.database.table.BaseTable
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.id.EntityID

abstract class BaseEntity(id: EntityID<Long>, table: BaseTable) : LongEntity(id) {
    val createdAt by table.createdAt
    var updatedAt by table.updatedAt
}
