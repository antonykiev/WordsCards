package com.words.cards.presentation.reducer

import com.words.cards.domain.GetSplashLoadedUseCase
import com.words.cards.presentation.event.MainEvent
import com.words.cards.presentation.intent.MainIntent
import com.words.cards.presentation.state.State
import kotlinx.coroutines.flow.MutableStateFlow

class MainReducer(
    private val getSplashLoadedUseCase: GetSplashLoadedUseCase
) : Reducer<MainEvent, Unit, MainIntent> {
    override val mutableState: MutableStateFlow<State<Unit, MainEvent>> = MutableStateFlow(
        State(
            isLoading = true,
            event = MainEvent.Empty,
            content = Unit
        )
    )

    override suspend fun processIntent(intent: MainIntent) {
        when (intent) {
            MainIntent.SplashLoaded -> {
                updateEvent(getSplashLoadedUseCase.invoke())
            }

            MainIntent.ContinueAsGuest -> TODO()
            MainIntent.ContinueAsUser -> TODO()
        }
    }
}