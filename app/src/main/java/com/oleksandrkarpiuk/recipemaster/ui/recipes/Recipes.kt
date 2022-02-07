package com.oleksandrkarpiuk.recipemaster.ui.recipes

import com.oleksandrkarpiuk.recipemaster.models.RecipeItem

data class Recipes(
    val recipes: List<RecipeItem> = listOf(),
    val isCategory: Boolean = false
)