package com.words.cards.android.login

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.words.cards.android.design.CardButton
import com.words.cards.android.design.CardInput
import com.words.cards.android.design.Logo
import com.words.cards.presentation.event.LoginEvent
import com.words.cards.presentation.intent.LoginIntent
import com.words.cards.presentation.state.InputStatus
import com.words.cards.presentation.state.LoginScreenContent
import com.words.cards.presentation.state.State
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onLoginSuccessfully: () -> Unit,
    onContinueAsGuest: () -> Unit,
) {
    val viewModel: LoginViewModel = koinViewModel<LoginViewModel>()
    val state: State<LoginScreenContent, LoginEvent> by viewModel.state.collectAsStateWithLifecycle()


    LaunchedEffect(Unit) {
        viewModel.onIntent(LoginIntent.InitialLoad)
    }

    LaunchedEffect(state.event) {
        when (val event = state.event) {
            LoginEvent.Empty -> {}
            LoginEvent.GoToWordList -> {
                onLoginSuccessfully()
            }
            is LoginEvent.OnLoginFailed -> TODO()
            is LoginEvent.OnLoginFieldIsNotValid -> TODO()
            LoginEvent.OnLoginSuccess -> onLoginSuccessfully()
            is LoginEvent.OnPasswordFieldIsNotValid -> TODO()
            LoginEvent.GoToSettings -> onContinueAsGuest()
        }
    }

    LoginPane(
        modifier = modifier,
        content = state.content,
        onEmailChange = {
            Log.d("LoginScreen", "onEmailChange: $it")
            viewModel.onIntent(LoginIntent.OnLoginChanged(it))
        },
        onPasswordChange = {
            viewModel.onIntent(LoginIntent.OnPasswordChanged(it))
        },
        onLoginClick = {
            onLoginSuccessfully()
//            viewModel.onIntent(LoginIntent.OnLoginClicked)
        },
        onContinueAsGuest = {
            viewModel.onIntent(LoginIntent.OnContinueAsGuestClicked)
        }
    )
}

@Composable
fun LoginPane(
    modifier: Modifier = Modifier,
    content: LoginScreenContent,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onContinueAsGuest: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            Logo(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(Modifier.height(24.dp))
            CardInput(
                modifier = Modifier,
                label = content.loginTint,
                text = content.loginInputText,
                onTextChange = onEmailChange
            )
            Spacer(Modifier.height(24.dp))
            CardInput(
                modifier = Modifier,
                label = content.passwordTint,
                text = content.passwordInputText,
                isPassword = true,
                onTextChange = onPasswordChange
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(bottom = 24.dp),
        ) {
            CardButton(
                modifier = Modifier
                    .fillMaxWidth(),
                text = content.loginButtonText,
                onClick = onLoginClick
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            CardButton(
                modifier = Modifier
                    .fillMaxWidth(),
                text = content.guestButtonText,
                onClick = onContinueAsGuest
            )
        }
    }
}

@Preview
@Composable
private fun LoginPanePreview() {
    LoginPane(
        content = LoginScreenContent(
            title = "Welcome to Words Cards",
            description = "In this app you can learn new words combining AI advantages and your effort",
            loginTint = "Email",
            loginInputText = "1mail@mail.com",
            loginInputStatus = InputStatus.Initial,
            passwordTint = "Password",
            passwordInputText = "4567",
            passwordInputStatus = InputStatus.Initial,
            loginButtonText = "LOGIN",
            guestButtonText = "Continue as guest",
        ),
        onEmailChange = {},
        onPasswordChange = {},
        onLoginClick = {},
        onContinueAsGuest = {}
    )
}