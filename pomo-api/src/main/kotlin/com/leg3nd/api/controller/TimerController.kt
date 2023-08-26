package com.leg3nd.api.controller

import com.leg3nd.api.dto.CreateTimerEventRequestDto
import com.leg3nd.api.dto.StartAnonymousTimerRequestDto
import com.leg3nd.api.dto.StartSignedTimerRequestDto
import com.leg3nd.api.dto.TimerEventResponseDto
import com.leg3nd.api.dto.TimerResponseDto
import com.leg3nd.api.dto.TimerSnapshotResponseDto
import com.leg3nd.api.mapper.PomodoroTimerEventMapper
import com.leg3nd.api.mapper.PomodoroTimerMapper
import com.leg3nd.domain.application.dto.CreateTimerEventRequest
import com.leg3nd.domain.application.dto.StartAnonymousTimerRequest
import com.leg3nd.domain.application.dto.StartSignedTimerRequest
import com.leg3nd.domain.ports.application.TimerApplicationPort
import org.koin.core.annotation.Single

@Single
class TimerController(
    private val timerApplicationPort: TimerApplicationPort,
) {
    suspend fun startAnonymousTimer(startAnonymousTimerRequestDto: StartAnonymousTimerRequestDto): TimerResponseDto {
        val domainDto = with(startAnonymousTimerRequestDto) {
            StartAnonymousTimerRequest(
                universalId,
                workDuration,
                breakDuration,
                repeatCount,
            )
        }

        val startedTimer = timerApplicationPort.startAnonymousTimer(domainDto)

        return PomodoroTimerMapper.mapToResponseDto(startedTimer)
    }

    suspend fun startSignedTimer(startSignedTimerRequestDto: StartSignedTimerRequestDto): TimerResponseDto {
        val domainDto = with(startSignedTimerRequestDto) {
            StartSignedTimerRequest(
                universalId,
                templateId,
            )
        }

        val startedTimer = timerApplicationPort.startSignedTimer(domainDto)

        return PomodoroTimerMapper.mapToResponseDto(startedTimer)
    }

    suspend fun createTimerEvent(createTimerEventRequestDto: CreateTimerEventRequestDto): TimerEventResponseDto {
        val domainDto = with(createTimerEventRequestDto) {
            CreateTimerEventRequest(
                universalId,
                timerId,
                eventType,
            )
        }

        val createdEvent = timerApplicationPort.createTimerEvent(domainDto)

        return PomodoroTimerEventMapper.mapToResponseDto(createdEvent)
    }

    suspend fun getTimerSnapshotById(timerId: Long): TimerSnapshotResponseDto {
        val foundSnapshot = timerApplicationPort.getTimerSnapshotById(timerId)

        return TimerSnapshotResponseDto(
            timer = PomodoroTimerMapper.mapToResponseDto(foundSnapshot.timer),
            events = foundSnapshot.events.map { PomodoroTimerEventMapper.mapToResponseDto(it) },
        )
    }

    suspend fun getLastStartedTimerSnapshot(universalId: String): TimerSnapshotResponseDto {
        val foundSnapshot = timerApplicationPort.getLastStartedTimerSnapshot(universalId)

        return TimerSnapshotResponseDto(
            timer = PomodoroTimerMapper.mapToResponseDto(foundSnapshot.timer),
            events = foundSnapshot.events.map { PomodoroTimerEventMapper.mapToResponseDto(it) },
        )
    }
}
