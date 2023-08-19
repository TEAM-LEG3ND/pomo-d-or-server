package com.leg3nd.infrastructure.database.table

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.timestamp

abstract class BaseTable : LongIdTable() {
    val createdAt = timestamp("created_at")
    val updatedAt = timestamp("updated_at")
}
