package com.oleksandrkarpiuk.recipemaster.ui.recipe

import android.content.Context
import android.content.Intent

internal object ExtraKeys {
    const val EXTRA_RECIPE_ID_KEY = "recipe_id_key"
}

internal data class Extras(
    val recipeId: Int
)

internal fun extractExtras(intent: Intent) : Extras {
    return Extras(
        recipeId = intent.getIntExtra(ExtraKeys.EXTRA_RECIPE_ID_KEY, 0)
    )
}

fun RecipeActivity.Companion.newInstance(context: Context, recipeId: Int): Intent {
    return Intent(context, RecipeActivity::class.java).apply {
        putExtra(ExtraKeys.EXTRA_RECIPE_ID_KEY, recipeId)
    }
}