package com.words.cards.android.wordlist

import com.words.cards.presentation.reducer.WordListReducer
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val wordsListModule = module {
    viewModel { WordLIstViewModel(get<WordListReducer>()) }
}