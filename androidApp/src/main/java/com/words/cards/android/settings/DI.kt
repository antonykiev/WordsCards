package com.words.cards.android.settings

import com.words.cards.presentation.reducer.SettingsReducer
import com.words.cards.presentation.reducer.SplashReducer
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val settingsModule = module {
    viewModel { SettingsViewModel(get<SettingsReducer>()) }
}