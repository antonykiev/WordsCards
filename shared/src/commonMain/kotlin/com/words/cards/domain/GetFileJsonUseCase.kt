package com.words.cards.domain

import com.words.cards.resource.AssetReader
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject

class GetFileJsonUseCase(
    private val assetReader: AssetReader,
) {
    fun loadJsonFromAssets(fileName: String): Result<JsonObject> {
        return runCatching {
            Json.decodeFromString(
                deserializer = JsonObject.serializer(),
                string = assetReader.readFile(fileName)
            )
        }
    }
}