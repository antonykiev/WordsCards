package com.words.cards.presentation.intent

sealed class LoginIntent {
    data object InitialLoad: LoginIntent()
    data object OnLoginClicked: LoginIntent()
    data class OnLoginChanged(val login: String): LoginIntent()
    data class OnPasswordChanged(val password: String): LoginIntent()
}