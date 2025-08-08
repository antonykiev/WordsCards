package com.words.cards.domain

import kotlinx.coroutines.delay

class CheckUserIsLoginUseCase {
    suspend operator fun invoke(): Boolean {
        delay(1000)
        return false
    }
}