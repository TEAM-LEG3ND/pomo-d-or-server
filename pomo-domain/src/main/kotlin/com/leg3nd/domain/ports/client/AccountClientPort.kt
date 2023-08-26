package com.leg3nd.domain.ports.client

interface AccountClientPort {
    suspend fun addServiceToAccount(universalId: String)
}
