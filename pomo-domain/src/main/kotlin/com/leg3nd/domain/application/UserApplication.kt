package com.leg3nd.domain.application

import com.leg3nd.domain.application.dto.CreateUserRequest
import com.leg3nd.domain.core.service.UserService
import com.leg3nd.domain.ports.application.UserApplicationPort
import com.leg3nd.domain.ports.client.AccountClientPort
import com.leg3nd.domain.ports.database.TransactionPort
import org.koin.core.annotation.Single

@Single
class UserApplication(
    private val userService: UserService,
    private val accountClientPort: AccountClientPort,
    private val transactionPort: TransactionPort,
) : UserApplicationPort {

    override suspend fun signUp(newUser: CreateUserRequest) = transactionPort.withNewTransaction {
        userService.create(newUser.universalId, newUser.nickname)
        accountClientPort.addServiceToAccount(newUser.universalId)
    }
}
