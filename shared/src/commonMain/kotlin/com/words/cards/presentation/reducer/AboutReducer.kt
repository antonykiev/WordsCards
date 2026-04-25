package com.words.cards.presentation.reducer

import com.words.cards.presentation.event.AboutEvent
import com.words.cards.presentation.intent.AboutIntent
import com.words.cards.presentation.state.AboutScreenContent
import com.words.cards.presentation.state.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class AboutReducer : Reducer<AboutEvent, AboutScreenContent, AboutIntent> {
    override val mutableState: MutableStateFlow<State<AboutScreenContent, AboutEvent>> =
        MutableStateFlow(
            State(
                isLoading = true,
                event = AboutEvent.Empty,
                content = AboutScreenContent.INITIAL
            )
        )

    override suspend fun processIntent(intent: AboutIntent) {
        when (intent) {
            AboutIntent.InitialLoad -> {
                mutableState.update {
                    it.copy(
                        isLoading = false,
                        content = AboutScreenContent(
                            title = "About Words Cards",
                            description = "Words Cards is an app designed to help you learn new vocabulary using AI."
                        )
                    )
                }
            }

            AboutIntent.OnBackClicked -> {
                updateEvent(AboutEvent.GoBack)
            }
        }
    }
}
