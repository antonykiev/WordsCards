package com.words.cards.data.repository

import com.words.cards.data.datasource.SettingsDataSource
import com.words.cards.data.db.entity.SettingsEntity

class SettingsLocalRepositoryImpl(
    private val dataSource: SettingsDataSource
) : SettingsLocalRepository {

    override suspend fun saveSettings(settings: SettingsEntity) {
        dataSource.saveSettings(settings)
    }

    override suspend fun getSettings(): Result<SettingsEntity> {
        return dataSource.getSettings()
    }

    override suspend fun updateSettings(settings: SettingsEntity) {
        dataSource.updateSettings(settings)
    }

    override suspend fun deleteSettings() {
        dataSource.deleteSettings()
    }
}