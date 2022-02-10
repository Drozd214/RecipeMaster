package com.oleksandrkarpiuk.recipemaster.ui.recipes

import android.content.Context
import android.content.Intent

internal object ExtraKeys {
    const val EXTRA_TITLE_KEY = "title_key"
    const val EXTRA_TAG_KEY = "tag_key"
}

internal data class Extras(
    val title: String,
    val tag: String
)

internal fun extractExtras(intent: Intent) : Extras {
    return Extras(
        title = intent.getStringExtra(ExtraKeys.EXTRA_TITLE_KEY) ?: "",
        tag = intent.getStringExtra(ExtraKeys.EXTRA_TAG_KEY) ?: "",
    )
}

fun RecipesContainerActivity.Companion.newInstance(context: Context, title: String, tag: String): Intent {
    return Intent(context, RecipesContainerActivity::class.java).apply {
        putExtra(ExtraKeys.EXTRA_TITLE_KEY, title)
        putExtra(ExtraKeys.EXTRA_TAG_KEY, tag)
    }
}