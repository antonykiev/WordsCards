package com.words.cards.presentation.event

sealed interface LoginEvent {
    data object Empty: LoginEvent
    data object OnLoginSuccess: LoginEvent
    data object GoToWordList : LoginEvent
    data object GoToSettings : LoginEvent
    data class OnLoginFieldIsNotValid(val message: String): LoginEvent
    data class OnPasswordFieldIsNotValid(val message: String): LoginEvent
    data class OnLoginFailed(val message: String): LoginEvent
}