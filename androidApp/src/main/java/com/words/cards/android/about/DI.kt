package com.words.cards.android.about

import com.words.cards.presentation.reducer.AboutReducer
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val aboutModule = module {
    viewModel { AboutViewModel(get<AboutReducer>()) }
}
