package com.leg3nd.infrastructure.database.table

import com.leg3nd.domain.core.model.PomodoroTimerTemplate
import org.jetbrains.exposed.sql.Column

object PomodoroTimerTemplates : BaseTable() {
    val type: Column<PomodoroTimerTemplate.Type> = enumerationByName<PomodoroTimerTemplate.Type>("type", 20)
    val author = reference("author", Users)
    val title: Column<String> = varchar("title", 30)
    val workDuration: Column<Long> = long("work_duration")
    val breakDuration: Column<Long> = long("break_duration")
    val repeatCount: Column<Long> = long("repeat_count")
}
