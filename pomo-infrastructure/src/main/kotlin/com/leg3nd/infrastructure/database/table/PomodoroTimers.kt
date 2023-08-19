package com.leg3nd.infrastructure.database.table

import com.leg3nd.domain.core.model.PomodoroTimer
import org.jetbrains.exposed.sql.Column

object PomodoroTimers : BaseTable() {
    val author = reference("author", Users)
    val template = reference("template", PomodoroTimerTemplates)
    val status: Column<PomodoroTimer.Status> = enumerationByName<PomodoroTimer.Status>("status", 20)
}
