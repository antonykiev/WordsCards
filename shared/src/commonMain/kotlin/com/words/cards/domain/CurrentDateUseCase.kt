package com.words.cards.domain

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atTime
import kotlinx.datetime.toInstant
import kotlinx.datetime.todayIn

interface CurrentDate {
    fun seconds(): Long
}

class CurrentDateUseCase: CurrentDate {
    private val currentDate: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())

    override fun seconds(): Long {
        return currentDate
            .atTime(0, 0, 0)
            .toInstant(TimeZone.UTC)
            .epochSeconds
    }
}

