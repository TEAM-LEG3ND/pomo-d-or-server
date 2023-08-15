package com.leg3nd.domain.ports.database

interface TransactionPort {
    fun <T> runOnTransaction(statement: () -> T): T
}
