package com.leg3nd.domain.core.model

import java.time.OffsetDateTime

data class Timer(
    val id: Long,
    val author: User,
    val template: TimerTemplate,
    val createdAt: OffsetDateTime,
    val updatedAt: OffsetDateTime,
)
