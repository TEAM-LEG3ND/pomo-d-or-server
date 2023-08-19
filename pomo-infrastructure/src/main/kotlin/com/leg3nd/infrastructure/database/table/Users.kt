package com.leg3nd.infrastructure.database.table

import org.jetbrains.exposed.sql.Column

object Users : BaseTable() {
    val universalId: Column<String> = varchar("universal_id", 30)
    val nickname: Column<String> = varchar("nickname", 20)
}
