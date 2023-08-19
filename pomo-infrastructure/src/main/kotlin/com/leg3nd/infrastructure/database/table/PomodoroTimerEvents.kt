package com.leg3nd.infrastructure.database.table

import com.leg3nd.domain.core.model.PomodoroTimerEvent
import org.jetbrains.exposed.sql.Column

object PomodoroTimerEvents : BaseTable() {
    val author = reference("author", Users)
    val timer = reference("timer", PomodoroTimers)
    val type: Column<PomodoroTimerEvent.Type> = enumerationByName<PomodoroTimerEvent.Type>("type", 20)
}
