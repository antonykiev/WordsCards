package com.words.cards.presentation.state

data class LoginScreenContent(
    val title: String,
    val description: String,
    val loginTint: String,
    val loginInputText: String,
    val loginInputStatus: InputStatus,
    val passwordTint: String,
    val passwordInputText: String,
    val passwordInputStatus: InputStatus,
    val loginButtonText: String,
) {
    companion object {
        val INITIAL = LoginScreenContent(
            title = "",
            description = "",
            loginTint = "",
            loginInputText = "",
            loginInputStatus = InputStatus.Initial,
            passwordTint = "",
            passwordInputText = "",
            passwordInputStatus = InputStatus.Initial,
            loginButtonText = "",
        )
    }
}

sealed interface InputStatus {
    data object Initial : InputStatus
    data object Valid : InputStatus
    data class Invalid(val errorMessage: String) : InputStatus
}
