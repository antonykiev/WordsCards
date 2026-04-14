package com.words.cards.domain

import com.words.cards.data.db.dao.LanguageDao
import com.words.cards.data.db.entity.LanguageEntity

class InitializeLanguagesUseCase(
    private val languageDao: LanguageDao
) {
    suspend operator fun invoke() {
        if (languageDao.getCount() == 0) {
            val languages = AppLanguages.entries.map {
                LanguageEntity(
                    id = it.id,
                    flag = it.flag,
                    name = it.languageName
                )
            }
            languageDao.insertAll(languages)
        }
    }
}
