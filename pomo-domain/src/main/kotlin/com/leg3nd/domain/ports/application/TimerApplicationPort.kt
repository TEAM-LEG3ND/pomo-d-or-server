package com.leg3nd.domain.ports.application

import com.leg3nd.domain.application.dto.CreateTimerEventRequest
import com.leg3nd.domain.application.dto.StartAnonymousTimerRequest
import com.leg3nd.domain.application.dto.StartSignedTimerRequest
import com.leg3nd.domain.application.dto.TimerSnapshotResponse
import com.leg3nd.domain.core.model.PomodoroTimer
import com.leg3nd.domain.core.model.PomodoroTimerEvent

interface TimerApplicationPort {
    fun startAnonymousTimer(startAnonymousTimerRequest: StartAnonymousTimerRequest): PomodoroTimer

    fun startSignedTimer(startSignedTimerRequest: StartSignedTimerRequest): PomodoroTimer

    fun createTimerEvent(createTimerEventRequest: CreateTimerEventRequest): PomodoroTimerEvent

    fun getTimerSnapshotById(timerId: Long): TimerSnapshotResponse

    fun getLastStartedTimerSnapshot(universalId: String): TimerSnapshotResponse
}
