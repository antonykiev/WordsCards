package com.words.cards.domain

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atTime
import kotlinx.datetime.toInstant
import kotlinx.datetime.todayIn

class CurrentDateUseCase {
    fun inSeconds(): Long {
        val today: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val localDateTime = today.atTime(0, 0, 0)
        val instant: Instant = localDateTime.toInstant(TimeZone.UTC)
        return instant.epochSeconds
    }
}