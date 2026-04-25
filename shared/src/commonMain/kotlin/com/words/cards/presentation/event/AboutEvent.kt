package com.words.cards.presentation.event

sealed interface AboutEvent {
    data object Empty : AboutEvent
    data object GoBack : AboutEvent
}
