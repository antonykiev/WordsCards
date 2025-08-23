package com.words.cards.domain.repository

import com.words.cards.data.db.entity.WordEntity
import com.words.cards.domain.entity.WordDomain
import kotlinx.coroutines.flow.Flow

interface WordRepository {
    fun getAllWords(): Flow<List<WordEntity>>
    suspend fun getWordById(id: Long): Result<WordEntity?>
    suspend fun insertWord(wordEntity: WordEntity): Result<Long>
    suspend fun updateWord(word: WordEntity): Result<Int>
    suspend fun deleteWord(word: WordEntity): Result<Int>

    companion object Mapper {
        fun mapToWordEntity(wordDomain: WordDomain): WordEntity {
            return WordEntity(
                id = wordDomain.id,
                wordText = wordDomain.wordText,
                wordDescription = wordDomain.wordDescription,
                wordTranslation = wordDomain.wordTranslation,
                wordTranscription = wordDomain.wordTranscription,
                createdAt = wordDomain.createdAt
            )
        }

        fun mapToWordDomain(wordEntity: WordEntity): WordDomain  {
            return WordDomain(
                id = wordEntity.id,
                wordText = wordEntity.wordText,
                wordDescription = wordEntity.wordDescription,
                wordTranslation = wordEntity.wordTranslation,
                wordTranscription = wordEntity.wordTranscription,
                createdAt = wordEntity.createdAt
            )
        }
    }
}