package com.words.cards.resource

expect class AssetReader {
    fun readFile(fileName: String): String
}