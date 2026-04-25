package com.words.cards.presentation.reducer

import com.words.cards.presentation.event.CardSettingsEvent
import com.words.cards.presentation.intent.CardSettingsIntent
import com.words.cards.presentation.state.CardSettingsScreenContent
import com.words.cards.presentation.state.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class CardSettingsReducer : Reducer<CardSettingsEvent, CardSettingsScreenContent, CardSettingsIntent> {
    override val mutableState: MutableStateFlow<State<CardSettingsScreenContent, CardSettingsEvent>> =
        MutableStateFlow(
            State(
                isLoading = true,
                event = CardSettingsEvent.Empty,
                content = CardSettingsScreenContent.INITIAL
            )
        )

    override suspend fun processIntent(intent: CardSettingsIntent) {
        when (intent) {
            CardSettingsIntent.InitialLoad -> {
                mutableState.update {
                    it.copy(
                        isLoading = false,
                        content = CardSettingsScreenContent(
                            title = "Card Settings",
                            showTranscriptionLabel = "Show Transcription",
                            isShowTranscriptionEnabled = true,
                            showDescriptionLabel = "Show Description",
                            isShowDescriptionEnabled = true,
                            showTranslationLabel = "Show Translation",
                            isShowTranslationEnabled = true,
                            showExampleLabel = "Show Example",
                            isShowExampleEnabled = true
                        )
                    )
                }
            }

            CardSettingsIntent.OnBackClicked -> {
                updateEvent(CardSettingsEvent.GoBack)
            }

            is CardSettingsIntent.OnShowTranscriptionToggled -> {
                mutableState.update {
                    it.copy(
                        content = it.content.copy(
                            isShowTranscriptionEnabled = intent.show
                        )
                    )
                }
            }

            is CardSettingsIntent.OnShowDescriptionToggled -> {
                mutableState.update {
                    it.copy(
                        content = it.content.copy(
                            isShowDescriptionEnabled = intent.show
                        )
                    )
                }
            }

            is CardSettingsIntent.OnShowTranslationToggled -> {
                mutableState.update {
                    it.copy(
                        content = it.content.copy(
                            isShowTranslationEnabled = intent.show
                        )
                    )
                }
            }

            is CardSettingsIntent.OnShowExampleToggled -> {
                mutableState.update {
                    it.copy(
                        content = it.content.copy(
                            isShowExampleEnabled = intent.show
                        )
                    )
                }
            }
        }
    }
}
