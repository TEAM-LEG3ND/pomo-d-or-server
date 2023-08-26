package com.leg3nd.api.mapper

import com.leg3nd.api.dto.TimerEventResponseDto
import com.leg3nd.common.util.DateTimeUtil.toEpochMilli
import com.leg3nd.domain.core.model.PomodoroTimerEvent

object PomodoroTimerEventMapper {
    fun mapToResponseDto(domain: PomodoroTimerEvent): TimerEventResponseDto = with(domain) {
        TimerEventResponseDto(
            type = type,
            createdAt = createdAt.toEpochMilli(),
        )
    }
}
