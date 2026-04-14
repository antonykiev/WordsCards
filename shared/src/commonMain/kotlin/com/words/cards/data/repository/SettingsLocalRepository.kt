package com.words.cards.data.repository

import com.words.cards.data.db.entity.SettingsEntity

interface SettingsLocalRepository {
    suspend fun saveSettings(settings: SettingsEntity)
    suspend fun getSettings(): Result<SettingsEntity>
    suspend fun updateSettings(settings: SettingsEntity)
    suspend fun deleteSettings()
}