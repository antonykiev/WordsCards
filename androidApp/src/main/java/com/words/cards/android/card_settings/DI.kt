package com.words.cards.android.card_settings

import com.words.cards.presentation.reducer.CardSettingsReducer
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val cardSettingsModule = module {
    viewModel { CardSettingsViewModel(get<CardSettingsReducer>()) }
}
