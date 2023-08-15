package com.leg3nd.domain.model

import java.time.OffsetDateTime

data class TimerTemplate(
    val id: Long,
    val type: Type,
    val author: User,
    val title: String?,
    val workDuration: Long,
    val breakDuration: Long,
    val repeatCount: Long,
    val createdAt: OffsetDateTime,
    val updatedAt: OffsetDateTime,
) {
    enum class Type {
        ANONYMOUS,
        SIGNED,
    }
}
