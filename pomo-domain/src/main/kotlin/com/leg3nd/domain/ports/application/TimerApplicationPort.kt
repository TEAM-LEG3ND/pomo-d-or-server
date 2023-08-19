package com.leg3nd.domain.ports.application

import com.leg3nd.domain.application.dto.CreateTimerEventRequest
import com.leg3nd.domain.application.dto.StartAnonymousTimerRequest
import com.leg3nd.domain.application.dto.StartSignedTimerRequest
import com.leg3nd.domain.application.dto.TimerSnapshotResponse
import com.leg3nd.domain.core.model.PomodoroTimer
import com.leg3nd.domain.core.model.PomodoroTimerEvent

interface TimerApplicationPort {
    suspend fun startAnonymousTimer(startAnonymousTimerRequest: StartAnonymousTimerRequest): PomodoroTimer

    suspend fun startSignedTimer(startSignedTimerRequest: StartSignedTimerRequest): PomodoroTimer

    suspend fun createTimerEvent(createTimerEventRequest: CreateTimerEventRequest): PomodoroTimerEvent

    suspend fun getTimerSnapshotById(timerId: Long): TimerSnapshotResponse

    suspend fun getLastStartedTimerSnapshot(universalId: String): TimerSnapshotResponse
}
