package com.leg3nd.infrastructure.database.config

import com.leg3nd.domain.ports.database.TransactionPort
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.experimental.withSuspendTransaction
import org.jetbrains.exposed.sql.transactions.transactionManager
import org.koin.core.annotation.Property
import org.koin.core.annotation.Single

@Single
class DatabaseConnector(
    @Property("database.jdbcUrl") jdbcUrl: String,
    @Property("database.username") username: String,
    @Property("database.password") password: String,
) : TransactionPort {

    private val ds = HikariDataSource(
        HikariConfig().apply {
            this.jdbcUrl = jdbcUrl
            this.username = username
            this.password = password
            driverClassName = "com.mysql.cj.jdbc.Driver"
            maximumPoolSize = 20
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        },
    )
    private val db = Database.connect(ds)

    override suspend fun <T> withNewTransaction(block: suspend () -> T): T =
        try {
            newSuspendedTransaction(Dispatchers.IO, db = db) {
                block()
            }
        } catch (e: Exception) {
            // TODO: add domain error mapping
            throw e
        }

    override suspend fun <T> withExistingTransaction(block: suspend () -> T): T {
        val tx = db.transactionManager.currentOrNull()
            ?: throw IllegalStateException("withExistingTransaction(): no current transaction in context")

        if (tx.connection.isClosed) {
            throw IllegalStateException("withExistingTransaction(): connection used by current transaction is closed")
        }
        return try {
            tx.withSuspendTransaction(Dispatchers.IO) {
                block()
            }
        } catch (e: Exception) {
            // TODO: add domain error mapping
            throw e
        }
    }
}
