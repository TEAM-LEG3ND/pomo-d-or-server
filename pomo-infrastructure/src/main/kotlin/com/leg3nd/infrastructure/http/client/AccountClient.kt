package com.leg3nd.infrastructure.http.client

import com.leg3nd.domain.ports.client.AccountClientPort
import org.koin.core.annotation.Single

@Single
class AccountClient : AccountClientPort {
    override fun addServiceToAccount(universalId: String) {
        TODO("need to implement")
    }
}
