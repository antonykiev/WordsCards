package com.words.cards.presentation.reducer

import com.words.cards.presentation.event.SettingsEvent
import com.words.cards.presentation.intent.SettingsIntent
import com.words.cards.presentation.state.SettingsScreenContent
import com.words.cards.presentation.state.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class SettingsReducer : Reducer<SettingsEvent, SettingsScreenContent, SettingsIntent> {

    override val mutableState: MutableStateFlow<State<SettingsScreenContent, SettingsEvent>> =
        MutableStateFlow(
            State(
                isLoading = false,
                event = SettingsEvent.Empty,
                content = SettingsScreenContent.INITIAL
            )
        )

    override suspend fun processIntent(intent: SettingsIntent) {
        when (intent) {
            SettingsIntent.InitialLoad -> Unit
            is SettingsIntent.SelectLanguage -> {
                mutableState.update { state ->
                    val newContent = if (intent.isPrimary) {
                        state.content.copy(primary = intent.language)
                    } else {
                        state.content.copy(secondary = intent.language)
                    }
                    state.copy(content = newContent)
                }
            }
        }
    }
}