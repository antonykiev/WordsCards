package com.words.cards.domain

import com.words.cards.data.db.entity.SettingsEntity
import com.words.cards.data.db.entity.WordEntity
import com.words.cards.data.repository.SettingsLocalRepository
import com.words.cards.presentation.state.WordItem

class MapWordEntityToWordItem(
    private val settingsRepository: SettingsLocalRepository
) {

    suspend operator fun invoke(wordEntities: List<WordEntity>): List<WordItem> {
        val settings = settingsRepository.getSettings().getOrNull()
        return wordEntities.map {
            invoke(it, settings)
        }
    }

    operator fun invoke(wordEntity: WordEntity, settings: SettingsEntity? = null): WordItem {
        val showTranscription = settings?.showTranscription ?: true
        val showDescription = settings?.showDescription ?: true
        val showTranslation = settings?.showTranslation ?: true

        return WordItem(
            id = wordEntity.id,
            word = wordEntity.wordText,
            transcription = wordEntity.wordTranscription,
            showTranscription = showTranscription,
            translation = wordEntity.wordTranslation,
            showTranslation = showTranslation,
            description = wordEntity.wordDescription,
            showDescription = showDescription,
        )
    }
}
