package com.words.cards.presentation.reducer

import com.words.cards.domain.CheckUserIsLoginUseCase
import com.words.cards.presentation.event.SplashEvent
import com.words.cards.presentation.intent.SplashIntent
import com.words.cards.presentation.state.SplashScreenContent
import com.words.cards.presentation.state.State
import com.words.cards.presentation.state.UserLoginState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class SplashReducer(
    private val checkUserIsLoginUseCase: CheckUserIsLoginUseCase
): Reducer<SplashEvent, SplashScreenContent, SplashIntent> {

    override val mutableState: MutableStateFlow<State<SplashScreenContent, SplashEvent>> = MutableStateFlow(
        State(
            isLoading = true,
            event = SplashEvent.Empty,
            content = SplashScreenContent(
                userLoginState = UserLoginState.Initial
            )
        )
    )

    override suspend fun processIntent(intent: SplashIntent) {
        when (intent) {
            SplashIntent.InitialLoad -> onInitialLoadIntent()
        }
    }

    private suspend fun onInitialLoadIntent() {
        delay(1000L)
        mutableState.update {
            state.value.copy(
                isLoading = false,
                event = SplashEvent.LoadedSuccessfully
            )
        }
    }
}