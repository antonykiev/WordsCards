package com.words.cards.domain

class GetTranscriptionUseCase(
    private val getFileJsonUseCase: GetFileJsonUseCase,
    private val arpabetToIpaUseCase: ArpabetToIpaUseCase,
    private val removeQuotesUseCase: RemoveQuotesUseCase,
) {
    suspend operator fun invoke(word: String): Result<String> {
        return getFileJsonUseCase.loadJsonFromAssets("cmudict.json")
            .mapCatching {
                it[word].also {
                    println("WordReducer - JsonElement: $it")
                } ?: throw IllegalArgumentException("Word not found in dictionary")

            }
            .map {
                println("WordReducer - Word: $word, Arpabet: $it")
                "$it"
            }
            .map {
                val stringWithoutQuotes = removeQuotesUseCase.invoke(it)
                arpabetToIpaUseCase.invoke(stringWithoutQuotes).also {
                    println("WordReducer - result: $it")
                }
            }
    }
}