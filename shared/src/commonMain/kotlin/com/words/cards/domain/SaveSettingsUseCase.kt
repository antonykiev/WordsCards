package com.words.cards.domain

import com.words.cards.data.db.entity.SettingsEntity
import com.words.cards.data.repository.SettingsLocalRepository

class SaveSettingsUseCase(
    private val settingsRepository: SettingsLocalRepository
) {

    suspend operator fun invoke(
        learnedLanguageId: Long,
        originalLanguageId: Long,
    ) {
        settingsRepository.saveSettings(
            SettingsEntity(
                learnedLanguageId = learnedLanguageId.toInt(),
                originalLanguageId = originalLanguageId.toInt()
            )
        )
    }
}
