package com.words.cards.presentation.event

sealed interface WordEvent {
    data object Empty : WordEvent
}