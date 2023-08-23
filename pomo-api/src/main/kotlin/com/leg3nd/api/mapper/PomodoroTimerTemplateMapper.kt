package com.leg3nd.api.mapper

import com.leg3nd.api.dto.TimerTemplateResponseDto
import com.leg3nd.domain.core.model.PomodoroTimerTemplate

object PomodoroTimerTemplateMapper {
    fun mapToResponseDto(domain: PomodoroTimerTemplate): TimerTemplateResponseDto = with(domain) {
        TimerTemplateResponseDto(
            id = id,
            type = type,
            author = UserMapper.mapToResponseDto(author),
            title = title,
            workDuration = workDuration,
            breakDuration = breakDuration,
            repeatCount = repeatCount,
        )
    }
}
