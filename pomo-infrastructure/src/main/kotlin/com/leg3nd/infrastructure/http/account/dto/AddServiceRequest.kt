package com.leg3nd.infrastructure.http.account.dto

data class AddServiceRequest(
    val accountId: String,
    val serviceType: String = "POMODOR",
)
