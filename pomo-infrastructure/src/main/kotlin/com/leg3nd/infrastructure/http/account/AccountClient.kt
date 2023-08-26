package com.leg3nd.infrastructure.http.account

import com.leg3nd.domain.ports.client.AccountClientPort
import com.leg3nd.infrastructure.http.account.dto.AddServiceRequest
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy
import org.koin.core.annotation.Property
import org.koin.core.annotation.Single

@Single
class AccountClient(
    @Property("account.host") private val accountHost: String,
) : AccountClientPort {

    @OptIn(ExperimentalSerializationApi::class)
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(
                Json {
                    namingStrategy = JsonNamingStrategy.SnakeCase
                    ignoreUnknownKeys = true
                },
            )
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }

    override suspend fun addServiceToAccount(universalId: String) {
        client.post {
            url {
                protocol = URLProtocol.HTTPS
                host = accountHost
                path("internal/api/v1/account/service")
            }
            contentType(ContentType.Application.Json)
            setBody(AddServiceRequest(accountId = universalId))
        }
    }
}
