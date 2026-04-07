package com.words.cards.presentation.reducer

import com.words.cards.presentation.event.SettingsEvent
import com.words.cards.presentation.intent.SettingsIntent
import com.words.cards.presentation.state.SettingsScreenContent
import com.words.cards.presentation.state.State
import kotlinx.coroutines.flow.MutableStateFlow

class SettingsReducer: Reducer<SettingsEvent, SettingsScreenContent, SettingsIntent> {

    override val mutableState: MutableStateFlow<State<SettingsScreenContent, SettingsEvent>>
        get() = TODO("Not yet implemented")

    override suspend fun processIntent(intent: SettingsIntent) {
        TODO("Not yet implemented")
    }
}