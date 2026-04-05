package com.words.cards.domain

import com.words.cards.data.db.entity.WordEntity
import com.words.cards.presentation.state.WordItem

class MapWordEntityToWordItem {

    operator fun invoke(wordEntities: List<WordEntity>): List<WordItem> {
        return wordEntities.map {
            invoke(it)
        }
    }

    operator fun invoke(wordEntity: WordEntity): WordItem {
        return WordItem(
            id = wordEntity.id,
            word = wordEntity.wordText,
            transcription = wordEntity.wordTranscription,
            translation = wordEntity.wordTranslation,
            description = wordEntity.wordDescription
        )
    }
}