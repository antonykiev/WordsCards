package com.words.cards.presentation.event

sealed interface MainEvent {
    data object Empty: MainEvent
    data class OpenWord(
        val newWord: String
    ): MainEvent
}