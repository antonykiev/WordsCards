package com.words.cards.presentation.event

sealed interface NewWordEvent {
    data object Empty : NewWordEvent
    data object Saved : NewWordEvent
    data object GoBack : NewWordEvent
}