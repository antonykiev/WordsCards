package com.words.cards.presentation.event

sealed interface WordNewEvent {
    data object Empty : WordNewEvent
    data object Saved : WordNewEvent
}