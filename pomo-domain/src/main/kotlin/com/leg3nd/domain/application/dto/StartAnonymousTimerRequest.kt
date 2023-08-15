package com.leg3nd.domain.application.dto

data class StartAnonymousTimerRequest(
    val universalId: String,
    val workDuration: Long,
    val breakDuration: Long,
    val repeatCount: Long,
)
