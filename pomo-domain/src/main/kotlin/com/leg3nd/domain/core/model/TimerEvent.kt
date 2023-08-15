package com.leg3nd.domain.core.model

import java.time.OffsetDateTime

data class TimerEvent(
    val id: Long,
    val author: User,
    val timer: Timer,
    val type: Type,
    val createdAt: OffsetDateTime,
    val updatedAt: OffsetDateTime,
) {
    enum class Type {
        WORK_START,
        WORK_PAUSE,
        WORK_RESUME,
        WORK_STOP,

        BREAK_START,
        BREAK_PAUSE,
        BREAK_RESUME,
        BREAK_STOP,
    }
}
