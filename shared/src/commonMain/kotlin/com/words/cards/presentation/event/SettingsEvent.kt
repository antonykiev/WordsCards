package com.words.cards.presentation.event

sealed interface SettingsEvent {
    data object Empty : SettingsEvent
    data object OnNextClicked : SettingsEvent
}