package com.words.cards.presentation.event

sealed interface MainEvent {
    data object Empty: MainEvent
}