package com.words.cards.resource

import android.content.Context
import androidx.core.content.ContextCompat

actual class ResourceProvider(private val context: Context) {
    actual fun getString(id: String): String {
        val resId = context.resources.getIdentifier(id, "string", context.packageName)
        return context.getString(resId)
    }

    actual fun getDrawable(id: String): Image {
        val resId = context.resources.getIdentifier(id, "drawable", context.packageName)
        return Image(resId)
    }
}