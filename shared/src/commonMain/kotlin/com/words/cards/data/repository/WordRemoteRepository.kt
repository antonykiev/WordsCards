package com.words.cards.data.repository

import com.words.cards.data.datasource.RemoteWordInfoDataSource
import com.words.cards.presentation.entity.ChatPresentation
import kotlinx.serialization.json.Json

class WordRemoteRepository(
    private val remoteWordInfoDataSource: RemoteWordInfoDataSource,
) {

    suspend fun getWordInfo(word: String): Result<ChatPresentation> {
        return runCatching {
            val content =  remoteWordInfoDataSource.getWordInfo(word).choices.first()
                .message
                .content
                .removePrefix("```")
                .removeSuffix("```")
            println(content)

            Json.decodeFromString<ChatPresentation>(
                content
            )
        }
    }

    private fun mapToChatPresentation(jsonString: String): ChatPresentation {
        return Json.decodeFromString<ChatPresentation>(
            jsonString
        )
    }
}