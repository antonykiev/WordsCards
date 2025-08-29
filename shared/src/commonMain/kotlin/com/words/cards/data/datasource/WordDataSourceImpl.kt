package com.words.cards.data.datasource

import com.words.cards.data.db.AppDatabase
import com.words.cards.data.db.entity.WordEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class WordDataSourceImpl(
    private val database: AppDatabase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : WordDataSource {

    override fun getAllWords(): Flow<List<WordEntity>> {
        return database.wordDao().getAllWords()
    }

    override suspend fun getWordById(id: Long): Result<WordEntity?> {
        return runCatching {
            withContext(dispatcher) {
                database.wordDao().getWordById(id)
            }
        }
    }

    override suspend fun insertWord(word: WordEntity): Result<Long> {
        return withContext(dispatcher) {
            runCatching { database.wordDao().insertWord(word) }
        }
    }

    override suspend fun updateWord(word: WordEntity): Result<Int> {
        return withContext(dispatcher) {
            runCatching { database.wordDao().updateWord(word) }
        }
    }

    override suspend fun deleteWord(word: WordEntity): Result<Int> {
        return withContext(dispatcher) {
            runCatching { database.wordDao().deleteWord(word) }
        }
    }
}