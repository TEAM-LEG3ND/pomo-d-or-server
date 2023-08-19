package com.leg3nd.domain.ports.database

interface TransactionPort {
    suspend fun <T> withNewTransaction(block: suspend () -> T): T

    suspend fun <T> withExistingTransaction(block: suspend () -> T): T
}
