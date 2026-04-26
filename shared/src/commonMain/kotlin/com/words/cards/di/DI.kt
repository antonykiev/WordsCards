package com.words.cards.di

import com.words.cards.data.datasource.RemoteWordInfoDataSource
import com.words.cards.data.datasource.SettingsDataSource
import com.words.cards.data.datasource.SettingsDataSourceImpl
import com.words.cards.data.datasource.UserDataSource
import com.words.cards.data.datasource.UserDataSourceImpl
import com.words.cards.data.datasource.WordDataSource
import com.words.cards.data.datasource.WordDataSourceImpl
import com.words.cards.data.db.AppDatabase
import com.words.cards.data.repository.SettingsLocalRepository
import com.words.cards.data.repository.SettingsLocalRepositoryImpl
import com.words.cards.data.repository.UserLocalRepository
import com.words.cards.data.repository.UserLocalRepositoryImpl
import com.words.cards.data.repository.WordLocalRepositoryImpl
import com.words.cards.data.repository.WordRemoteRepository
import com.words.cards.domain.ArpabetToIpaUseCase
import com.words.cards.domain.CheckUserIsLoginUseCase
import com.words.cards.domain.CreateGuestUserUseCase
import com.words.cards.domain.CurrentDateUseCase
import com.words.cards.domain.GetFileJsonUseCase
import com.words.cards.domain.GetSplashLoadedUseCase
import com.words.cards.domain.GetTranscriptionUseCase
import com.words.cards.domain.MapWordEntityToWordItem
import com.words.cards.domain.RemoveQuotesUseCase
import com.words.cards.domain.SaveSettingsUseCase
import com.words.cards.domain.repository.WordLocalRepository
import com.words.cards.presentation.reducer.AboutReducer
import com.words.cards.presentation.reducer.CardSettingsReducer
import com.words.cards.presentation.reducer.LoginReducer
import com.words.cards.presentation.reducer.MainReducer
import com.words.cards.presentation.reducer.SplashReducer
import com.words.cards.presentation.reducer.WordListReducer
import com.words.cards.presentation.reducer.NewWordReducer
import com.words.cards.presentation.reducer.SettingsReducer
import com.words.cards.presentation.reducer.WordReducer
import com.words.cards.resource.AssetReader
import org.koin.dsl.module

val mainDomainModule = module {
    factory {
        MainReducer(
            getSplashLoadedUseCase = get<GetSplashLoadedUseCase>()
        )
    }
    factory {
        GetSplashLoadedUseCase(
            userRepository = get<UserLocalRepository>(),
        )
    }
}

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
            createGuestUserUseCase = get<CreateGuestUserUseCase>()
        )
    }
    factory {
        CreateGuestUserUseCase(
            userRepository = get<UserLocalRepository>()
        )
    }
}

val aboutDomainModule = module {
    factory {
        AboutReducer()
    }
}

val cardSettingsDomainModule = module {
    factory {
        CardSettingsReducer(
            saveSettingsUseCase = get<SaveSettingsUseCase>(),
            settingsRepository = get<SettingsLocalRepository>(),
        )
    }
}

val wordListDomainModule = module {
    factory {
        WordListReducer(
            wordLocalRepository = get<WordLocalRepository>(),
            mapWordEntityToWordItem = get<MapWordEntityToWordItem>(),
        )
    }
}
val newWordDomainModule = module {
    factory {
        NewWordReducer(
            getTranscriptionUseCase = get<GetTranscriptionUseCase>(),
            wordRemoteRepository = get<WordRemoteRepository>(),
            wordLocalRepository = get<WordLocalRepository>(),
            currentDateUseCase = get<CurrentDateUseCase>(),
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
    factory {
        CurrentDateUseCase()
    }
}

val wordDomainModule = module {
    factory {
        WordReducer(
            wordLocalRepository = get<WordLocalRepository>(),
        )
    }
    single {
        MapWordEntityToWordItem(
            settingsRepository = get<SettingsLocalRepository>()
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
    single<UserDataSource> {
        UserDataSourceImpl(
            database = get<AppDatabase>()
        )
    }
    single<SettingsDataSource> {
        SettingsDataSourceImpl(
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
    single<UserLocalRepository> {
        UserLocalRepositoryImpl(
            dataSource = get<UserDataSource>()
        )
    }
    single<SettingsLocalRepository> {
        SettingsLocalRepositoryImpl(
            dataSource = get<SettingsDataSource>()
        )
    }
}

val settingsDomainModule = module {
    factory {
        SettingsReducer(
            saveSettingsUseCase = get<SaveSettingsUseCase>()
        )
    }
    factory {
        SaveSettingsUseCase(
            settingsRepository = get<SettingsLocalRepository>()
        )
    }
}


val sharedDomainModule = buildList {
    add(splashDomainModule)
    add(mainDomainModule)
    add(loginDomainModule)
    add(aboutDomainModule)
    add(cardSettingsDomainModule)
    add(wordListDomainModule)
    add(newWordDomainModule)
    add(wordDomainModule)
    add(settingsDomainModule)
    add(dataSourceModule)
    add(repositoryModule)
}