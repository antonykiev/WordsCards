package com.words.cards.data.datasource

import com.words.cards.data.remote.requests.ChatRequest
import com.words.cards.data.remote.requests.MessageRequest
import com.words.cards.data.remote.response.ChatResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class RemoteWordInfoDataSource(

) {

    private companion object Constants {
        private val URL = "https://api.cerebras.ai/v1/chat/completions"
        private val TOKEN = "csk-fxv5ejcxc5jw93rymf5pdxev6h65e3fcwxpjf8h2nxpyp2hj"
        private val LMM_MODEL = "llama-4-scout-17b-16e-instruct"
        private fun promt(word: String) = """
            generate translation of word $word on Russian.
            write else English description what this word means.
            generate 5 English sentences with word $word.
            don`t give me any additional information only what I ask you.
            answer give me in JSON format with fields:
            - translation [type: String]
            - description [type: String]
            - sentences [type: list of String]
        """.trimIndent()

    }

    suspend fun getWordInfo(word: String): ChatResponse {
        val client = HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                    }
                )
            }
            install(Logging) {
                level = LogLevel.BODY
            }
        }

        val response: HttpResponse = client.post(URL) {
            contentType(ContentType.Application.Json)
            headers {
                append(
                    HttpHeaders.Authorization,
                    "Bearer $TOKEN"
                )
            }
            setBody(
                ChatRequest(
                    model = LMM_MODEL,
                    stream = false,
                    max_tokens = 2048,
                    temperature = 0.2,
                    top_p = 1.0,
                    messages = listOf(
                        MessageRequest(
                            role = "user",
                            content = promt(word)
                        )
                    )
                )
            )
        }
        println(
            "reponse: ${response}"
        )
        println(
            "reponse: ${response.body<ChatResponse>()}"
        )
        return response.body()
    }
}