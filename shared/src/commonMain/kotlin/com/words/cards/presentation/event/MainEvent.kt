package com.words.cards.presentation.event

sealed interface MainEvent {
    data object Empty : MainEvent
    data object GoToLogin : MainEvent
    data object GoToWordList : MainEvent
}