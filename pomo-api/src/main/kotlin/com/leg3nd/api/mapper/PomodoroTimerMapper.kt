package com.leg3nd.api.mapper

import com.leg3nd.api.dto.TimerResponseDto
import com.leg3nd.common.util.DateTimeUtil.toEpochMilli
import com.leg3nd.domain.core.model.PomodoroTimer

object PomodoroTimerMapper {
    fun mapToResponseDto(domain: PomodoroTimer): TimerResponseDto = with(domain) {
        TimerResponseDto(
            id = id,
            author = UserMapper.mapToResponseDto(author),
            template = PomodoroTimerTemplateMapper.mapToResponseDto(template),
            status = status,
            createdAt = createdAt.toEpochMilli(),
            updatedAt = updatedAt.toEpochMilli(),
        )
    }
}
