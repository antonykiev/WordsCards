package com.words.cards.presentation.event

interface LoginEvent {
    data object Empty: LoginEvent
    data object OnLoginSuccess: LoginEvent
    data class OnLoginFieldIsNotValid(val message: String): LoginEvent
    data class OnPasswordFieldIsNotValid(val message: String): LoginEvent
    data class OnLoginFailed(val message: String): LoginEvent
}