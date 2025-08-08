package com.words.cards.presentation.state

data class SplashScreenContent(
    val userLoginState: UserLoginState
)

sealed interface UserLoginState {
    data object Initial : UserLoginState
    data class Info(
        val isUserLoggedIn: Boolean
    ) : UserLoginState
}
