package com.words.cards.presentation.event

sealed interface CardSettingsEvent {
    data object Empty : CardSettingsEvent
    data object GoBack : CardSettingsEvent
}
