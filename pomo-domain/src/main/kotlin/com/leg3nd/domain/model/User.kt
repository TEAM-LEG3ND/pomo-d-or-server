package com.leg3nd.domain.model

import java.time.OffsetDateTime

data class User(
    val id: Long,
    val universalId: String,
    val nickname: String,
    val createdAt: OffsetDateTime,
    val updatedAt: OffsetDateTime,
)
