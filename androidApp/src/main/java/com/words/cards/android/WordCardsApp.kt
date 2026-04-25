package com.words.cards.android

import android.app.Application
import com.words.cards.android.about.aboutModule
import com.words.cards.android.card_settings.cardSettingsModule
import com.words.cards.android.login.loginAndroidModule
import com.words.cards.android.settings.settingsModule
import com.words.cards.android.splash.splashModule
import com.words.cards.android.word.wordModule
import com.words.cards.android.word_new.newWordModule
import com.words.cards.android.wordlist.wordsListModule
import com.words.cards.di.sharedDomainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WordCardsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                androidModule +
                        sharedDomainModule +
                        splashModule +
                        mainActivityModule +
                        settingsModule +
                        loginAndroidModule +
                        wordsListModule +
                        wordModule +
                        newWordModule +
                        aboutModule +
                        cardSettingsModule
            )
            androidContext(this@WordCardsApp)
        }
    }
}