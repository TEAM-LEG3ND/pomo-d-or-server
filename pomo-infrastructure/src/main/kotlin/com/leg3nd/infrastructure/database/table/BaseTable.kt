package com.leg3nd.infrastructure.database.table

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.timestamp
import java.time.OffsetDateTime

abstract class BaseTable : LongIdTable() {
    val createdAt = timestamp("created_at").clientDefault {
        OffsetDateTime.now().toInstant()
    }
    val updatedAt = timestamp("updated_at").clientDefault {
        OffsetDateTime.now().toInstant()
    }
}
