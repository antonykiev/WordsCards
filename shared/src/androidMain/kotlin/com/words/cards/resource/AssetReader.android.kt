package com.words.cards.resource

import android.content.res.AssetManager

actual class AssetReader(private val assetManager: AssetManager) {
    actual fun readFile(fileName: String): String {
        return assetManager.open(fileName).bufferedReader().use { it.readText() }
    }
}