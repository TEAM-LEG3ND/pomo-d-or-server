package com.leg3nd.api.dto

data class StartAnonymousTimerRequestDto(
    val universalId: String,
    val workDuration: Long,
    val breakDuration: Long,
    val repeatCount: Long,
)
