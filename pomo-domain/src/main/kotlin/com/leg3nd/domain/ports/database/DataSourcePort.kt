package com.leg3nd.domain.ports.database

interface DataSourcePort {
    fun <T> runOnTransaction(statement: () -> T): T
}
