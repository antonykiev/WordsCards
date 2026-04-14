package com.words.cards.data.datasource

import com.words.cards.data.db.AppDatabase
import com.words.cards.data.db.entity.SettingsEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class SettingsDataSourceImpl(
    private val database: AppDatabase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : SettingsDataSource {
    
    override suspend fun saveSettings(settings: SettingsEntity) {
        withContext(dispatcher) {
            database.settingsDao().insert(settings)
        }
    }

    override suspend fun getSettings(): Result<SettingsEntity> {
        return runCatching {
            withContext(dispatcher) {
                database.settingsDao().get()!!
            }
        }
    }

    override suspend fun updateSettings(settings: SettingsEntity) {
        withContext(dispatcher) {
            database.settingsDao().update(settings)
        }
    }

    override suspend fun deleteSettings() {
        withContext(dispatcher) {
            database.settingsDao().delete()
        }
    }
}
