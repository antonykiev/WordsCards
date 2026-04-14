package com.words.cards.data.datasource

import com.words.cards.data.db.entity.SettingsEntity

interface SettingsDataSource {
    suspend fun saveSettings(settings: SettingsEntity)
    suspend fun getSettings(): Result<SettingsEntity>
    suspend fun updateSettings(settings: SettingsEntity)
    suspend fun deleteSettings()
}
