package com.words.cards.domain

import com.words.cards.data.db.entity.SettingsEntity
import com.words.cards.data.repository.SettingsLocalRepository

class SaveSettingsUseCase(
    private val settingsRepository: SettingsLocalRepository
) {

    suspend fun save(
        learnedLanguageId: Long,
        originalLanguageId: Long,
    ) {
        settingsRepository.saveSettings(
            SettingsEntity(
                learnedLanguageId = learnedLanguageId.toInt(),
                originalLanguageId = originalLanguageId.toInt(),
                showTranscription = true,
                showDescription = true,
                showTranslation = true,
                showExample = true
            )
        )
    }

    suspend fun update(
        showTranscription: Boolean? = null,
        showDescription: Boolean? = null,
        showTranslation: Boolean? = null,
        showExample: Boolean? = null,
    ) {
        val currentSettings = settingsRepository.getSettings().getOrNull() ?: return

        settingsRepository.saveSettings(
            currentSettings.copy(
                showTranscription = showTranscription ?: currentSettings.showTranscription,
                showDescription = showDescription ?: currentSettings.showDescription,
                showTranslation = showTranslation ?: currentSettings.showTranslation,
                showExample = showExample ?: currentSettings.showExample
            )
        )
    }
}
