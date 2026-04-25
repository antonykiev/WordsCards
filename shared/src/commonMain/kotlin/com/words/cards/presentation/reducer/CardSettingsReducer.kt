package com.words.cards.presentation.reducer

import com.words.cards.data.repository.SettingsLocalRepository
import com.words.cards.domain.SaveSettingsUseCase
import com.words.cards.presentation.event.CardSettingsEvent
import com.words.cards.presentation.intent.CardSettingsIntent
import com.words.cards.presentation.state.CardSettingsScreenContent
import com.words.cards.presentation.state.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class CardSettingsReducer(
    private val saveSettingsUseCase: SaveSettingsUseCase,
    private val settingsRepository: SettingsLocalRepository,
) : Reducer<CardSettingsEvent, CardSettingsScreenContent, CardSettingsIntent> {
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
                val settings = settingsRepository.getSettings().getOrNull()
                mutableState.update {
                    it.copy(
                        isLoading = false,
                        content = CardSettingsScreenContent(
                            title = "Card Settings",
                            showTranscriptionLabel = "Show Transcription",
                            isShowTranscriptionEnabled = settings?.showTranscription ?: true,
                            showDescriptionLabel = "Show Description",
                            isShowDescriptionEnabled = settings?.showDescription ?: true,
                            showTranslationLabel = "Show Translation",
                            isShowTranslationEnabled = settings?.showTranslation ?: true,
                            showExampleLabel = "Show Example",
                            isShowExampleEnabled = settings?.showExample ?: true
                        )
                    )
                }
            }

            CardSettingsIntent.OnBackClicked -> {
                updateEvent(CardSettingsEvent.GoBack)
            }

            is CardSettingsIntent.OnShowTranscriptionToggled -> {
                saveSettingsUseCase.update(showTranscription = intent.show)
                mutableState.update {
                    it.copy(
                        content = it.content.copy(
                            isShowTranscriptionEnabled = intent.show
                        )
                    )
                }
            }

            is CardSettingsIntent.OnShowDescriptionToggled -> {
                saveSettingsUseCase.update(showDescription = intent.show)
                mutableState.update {
                    it.copy(
                        content = it.content.copy(
                            isShowDescriptionEnabled = intent.show
                        )
                    )
                }
            }

            is CardSettingsIntent.OnShowTranslationToggled -> {
                saveSettingsUseCase.update(showTranslation = intent.show)
                mutableState.update {
                    it.copy(
                        content = it.content.copy(
                            isShowTranslationEnabled = intent.show
                        )
                    )
                }
            }

            is CardSettingsIntent.OnShowExampleToggled -> {
                saveSettingsUseCase.update(showExample = intent.show)
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
