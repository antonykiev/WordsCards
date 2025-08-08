package com.words.cards.android.main

import com.words.cards.presentation.reducer.MainReducer
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel { MainViewModel(get<MainReducer>()) }
}