package com.words.cards.di

import com.words.cards.data.datasource.RemoteWordInfoDataSource
import com.words.cards.data.datasource.WordDataSource
import com.words.cards.data.datasource.WordDataSourceImpl
import com.words.cards.data.db.AppDatabase
import com.words.cards.data.repository.WordLocalRepositoryImpl
import com.words.cards.data.repository.WordRemoteRepository
import com.words.cards.domain.ArpabetToIpaUseCase
import com.words.cards.domain.CheckUserIsLoginUseCase
import com.words.cards.domain.GetFileJsonUseCase
import com.words.cards.domain.GetTranscriptionUseCase
import com.words.cards.domain.RemoveQuotesUseCase
import com.words.cards.domain.repository.WordLocalRepository
import com.words.cards.presentation.reducer.LoginReducer
import com.words.cards.presentation.reducer.SplashReducer
import com.words.cards.presentation.reducer.WordListReducer
import com.words.cards.presentation.reducer.NewWordReducer
import com.words.cards.presentation.reducer.WordReducer
import com.words.cards.resource.AssetReader
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

val wordListDomainModule = module {
    factory {
        WordListReducer(
            wordLocalRepository = get<WordLocalRepository>()
        )
    }
}
val newWordDomainModule = module {
    factory {
        NewWordReducer(
            getTranscriptionUseCase = get<GetTranscriptionUseCase>(),
            wordRemoteRepository = get<WordRemoteRepository>(),
            wordLocalRepository = get<WordLocalRepository>(),
        )
    }
    factory {
        GetTranscriptionUseCase(
            getFileJsonUseCase = get<GetFileJsonUseCase>(),
            arpabetToIpaUseCase = get<ArpabetToIpaUseCase>(),
            removeQuotesUseCase = get<RemoveQuotesUseCase>()
        )
    }
    factory {
        RemoveQuotesUseCase()
    }
    factory {
        ArpabetToIpaUseCase()
    }
    factory {
        GetFileJsonUseCase(
            assetReader = get<AssetReader>()
        )
    }
}

val wordDomainModule = module {
    factory {
        WordReducer(
            wordLocalRepository = get<WordLocalRepository>(),
        )
    }
}

val dataSourceModule = module {
    single {
        RemoteWordInfoDataSource()
    }
    single<WordDataSource> {
        WordDataSourceImpl(
            database = get<AppDatabase>()
        )
    }
}

val repositoryModule = module {
    single {
        WordRemoteRepository(
            remoteWordInfoDataSource = get<RemoteWordInfoDataSource>()
        )
    }
    single<WordLocalRepository> {
        WordLocalRepositoryImpl(
            dataSource = get<WordDataSource>()
        )
    }
}

val sharedDomainModule = buildList {
    add(splashDomainModule)
    add(loginDomainModule)
    add(wordListDomainModule)
    add(newWordDomainModule)
    add(wordDomainModule)
    add(dataSourceModule)
    add(repositoryModule)
}