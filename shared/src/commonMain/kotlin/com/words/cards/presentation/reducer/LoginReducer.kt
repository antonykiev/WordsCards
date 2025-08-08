package com.words.cards.presentation.reducer

import com.words.cards.presentation.event.LoginEvent
import com.words.cards.presentation.intent.LoginIntent
import com.words.cards.presentation.state.InputStatus
import com.words.cards.presentation.state.LoginScreenContent
import com.words.cards.presentation.state.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class LoginReducer : Reducer<LoginEvent, LoginScreenContent, LoginIntent> {
    override val mutableState: MutableStateFlow<State<LoginScreenContent, LoginEvent>> =
        MutableStateFlow(
            State(
                isLoading = true,
                event = LoginEvent.Empty,
                content = LoginScreenContent.INITIAL
            )
        )

    override suspend fun processIntent(intent: LoginIntent) {
        when (intent) {
            LoginIntent.InitialLoad -> {
                mutableState.update {
                    it.copy(
                        isLoading = false,
                        content = LoginScreenContent(
                            title = "Welcome to Words Cards",
                            description = "In this app you can learn new words combining AI advantages and your effort",
                            loginTint = "Email",
                            loginInputText = "",
                            loginInputStatus = InputStatus.Initial,
                            passwordTint = "Password",
                            passwordInputText = "",
                            passwordInputStatus = InputStatus.Initial,
                            loginButtonText = "Login",
                        )
                    )
                }
            }

            is LoginIntent.OnLoginChanged -> {
                mutableState.update {
                    it.copy(
                        content = it.content.copy(
                            loginInputText = intent.login
                        )
                    )
                }
            }

            LoginIntent.OnLoginClicked -> {

            }

            is LoginIntent.OnPasswordChanged -> {
                mutableState.update {
                    it.copy(
                        content = it.content.copy(
                            passwordInputText = intent.password
                        )
                    )
                }
            }
        }
    }
}