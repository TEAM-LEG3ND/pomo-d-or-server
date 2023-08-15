package com.leg3nd.domain.core.model

import java.time.OffsetDateTime

data class PomodoroTimer(
    val id: Long,
    val author: User,
    val template: PomodoroTimerTemplate,
    val status: Status,
    val createdAt: OffsetDateTime,
    val updatedAt: OffsetDateTime,
) {
    enum class Status {
        LIVE,
        DONE,
        TERMINATED,
    }
}
