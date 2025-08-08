package com.words.cards.di

import com.words.cards.domain.CheckUserIsLoginUseCase
import com.words.cards.presentation.reducer.LoginReducer
import com.words.cards.presentation.reducer.MainReducer
import com.words.cards.presentation.reducer.SplashReducer
import com.words.cards.presentation.reducer.WordListReducer
import com.words.cards.resource.ResourceProvider
import org.koin.core.module.Module
import org.koin.dsl.module

val splashDomainModule = module {
    factory { CheckUserIsLoginUseCase() }
    factory {
        SplashReducer(
            checkUserIsLoginUseCase = get<CheckUserIsLoginUseCase>()
        )
    }
}

val loginDomainModule = module {
    factory {
        LoginReducer(

        )
    }
}

val mainDomainModule = module {
    factory {
        MainReducer(
            resourceProvider = get<ResourceProvider>()
        )
    }
}

val wordListDomainModule = module {
    factory {
        WordListReducer(

        )
    }
}

val sharedDomainModule = buildList {
    add(splashDomainModule)
    add(loginDomainModule)
    add(mainDomainModule)
    add(wordListDomainModule)
}