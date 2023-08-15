package com.leg3nd.domain.application

import com.leg3nd.domain.application.dto.CreateTimerEventRequest
import com.leg3nd.domain.application.dto.StartAnonymousTimerRequest
import com.leg3nd.domain.application.dto.StartSignedTimerRequest
import com.leg3nd.domain.application.dto.TimerSnapshotResponse
import com.leg3nd.domain.core.model.PomodoroTimer
import com.leg3nd.domain.core.model.PomodoroTimerEvent
import com.leg3nd.domain.core.model.PomodoroTimerTemplate
import com.leg3nd.domain.core.model.User
import com.leg3nd.domain.core.service.PomodoroTimerEventService
import com.leg3nd.domain.core.service.PomodoroTimerService
import com.leg3nd.domain.core.service.PomodoroTimerTemplateService
import com.leg3nd.domain.core.service.UserService
import com.leg3nd.domain.exception.BaseException
import com.leg3nd.domain.ports.application.TimerApplicationPort
import com.leg3nd.domain.ports.database.TransactionPort
import java.time.OffsetDateTime

class TimerApplication(
    private val userService: UserService,
    private val pomodoroTimerService: PomodoroTimerService,
    private val pomodoroTimerEventService: PomodoroTimerEventService,
    private val pomodoroTimerTemplateService: PomodoroTimerTemplateService,
    private val transactionPort: TransactionPort,
) : TimerApplicationPort {
    companion object {
        private const val ANONYMOUS_TIMER_TITLE = "빠른 타이머"
    }

    override fun startAnonymousTimer(startAnonymousTimerRequest: StartAnonymousTimerRequest): PomodoroTimer {
        val user = userService.findByUniversalId(startAnonymousTimerRequest.universalId)

        val startedTimer = transactionPort.runOnTransaction {
            terminateOldTimers(user)
            val now = OffsetDateTime.now()

            val newAnonymousTimerTemplate = PomodoroTimerTemplate(
                id = 0L,
                type = PomodoroTimerTemplate.Type.ANONYMOUS,
                author = user,
                title = ANONYMOUS_TIMER_TITLE,
                workDuration = startAnonymousTimerRequest.workDuration,
                breakDuration = startAnonymousTimerRequest.breakDuration,
                repeatCount = startAnonymousTimerRequest.repeatCount,
                createdAt = now,
                updatedAt = now,
            )
            val createdAnonymousTimerTemplate = pomodoroTimerTemplateService.create(newAnonymousTimerTemplate)

            val newTimer = PomodoroTimer(
                id = 0L,
                author = user,
                template = createdAnonymousTimerTemplate,
                status = PomodoroTimer.Status.LIVE,
                createdAt = now,
                updatedAt = now,
            )
            val createdTimer = pomodoroTimerService.create(newTimer)

            val newTimerStartEvent = PomodoroTimerEvent(
                id = 0L,
                author = user,
                timer = createdTimer,
                type = PomodoroTimerEvent.Type.WORK_START,
                createdAt = now,
                updatedAt = now,
            )
            pomodoroTimerEventService.create(newTimerStartEvent)

            return@runOnTransaction createdTimer
        }

        return startedTimer
    }

    override fun startSignedTimer(startSignedTimerRequest: StartSignedTimerRequest): PomodoroTimer {
        val user = userService.findByUniversalId(startSignedTimerRequest.universalId)
        val signedTimerTemplate = pomodoroTimerTemplateService.findById(startSignedTimerRequest.templateId)

        if (user.id != signedTimerTemplate.author.id) {
            throw BaseException(BaseException.Type.BAD_REQUEST, "User ID does not match with template author ID")
        }

        val startedTimer = transactionPort.runOnTransaction {
            terminateOldTimers(user)
            val now = OffsetDateTime.now()

            val newTimer = PomodoroTimer(
                id = 0L,
                author = user,
                template = signedTimerTemplate,
                status = PomodoroTimer.Status.LIVE,
                createdAt = now,
                updatedAt = now,
            )
            val createdTimer = pomodoroTimerService.create(newTimer)

            val newTimerStartEvent = PomodoroTimerEvent(
                id = 0L,
                author = user,
                timer = createdTimer,
                type = PomodoroTimerEvent.Type.WORK_START,
                createdAt = now,
                updatedAt = now,
            )
            pomodoroTimerEventService.create(newTimerStartEvent)

            return@runOnTransaction createdTimer
        }

        return startedTimer
    }

    override fun createTimerEvent(createTimerEventRequest: CreateTimerEventRequest): PomodoroTimerEvent {
        val user = userService.findByUniversalId(createTimerEventRequest.universalId)
        val timer = pomodoroTimerService.findById(createTimerEventRequest.timerId)

        if (user.id != timer.id) {
            throw BaseException(BaseException.Type.BAD_REQUEST, "User ID does not match with timer author ID")
        }

        val now = OffsetDateTime.now()

        val newTimerEvent = PomodoroTimerEvent(
            id = 0L,
            author = user,
            timer = timer,
            type = createTimerEventRequest.eventType,
            createdAt = now,
            updatedAt = now,
        )

        return pomodoroTimerEventService.create(newTimerEvent)
    }

    override fun getTimerSnapshotById(timerId: Long): TimerSnapshotResponse {
        val timer = pomodoroTimerService.findById(timerId)
        val events = pomodoroTimerEventService.findByTimer(timer)

        return TimerSnapshotResponse(
            timer = timer,
            events = events,
        )
    }

    override fun getLastStartedTimerSnapshot(universalId: String): TimerSnapshotResponse {
        val user = userService.findByUniversalId(universalId)

        val timer = pomodoroTimerService.findLastStartedByAuthor(user)
        val events = pomodoroTimerEventService.findByTimer(timer)

        return TimerSnapshotResponse(
            timer = timer,
            events = events,
        )
    }

    private fun terminateOldTimers(user: User) {
        val timers = pomodoroTimerService.findByAuthorAndStatus(user, PomodoroTimer.Status.LIVE)
        val timerIds = timers.map {
            it.id
        }

        pomodoroTimerService.updateStatusByIds(timerIds, PomodoroTimer.Status.TERMINATED)
    }
}
