package com.leg3nd.api.dto

data class TimerSnapshotResponseDto(
    val timer: TimerResponseDto,
    val events: List<TimerEventResponseDto>,
)
