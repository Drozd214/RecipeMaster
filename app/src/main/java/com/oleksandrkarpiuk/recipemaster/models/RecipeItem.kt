package com.oleksandrkarpiuk.recipemaster.models

import java.io.Serializable

data class RecipeItem(
    val imageUrl: String?,
    val name: String,
    val score: Int = 0,
    val servings: Int = 0,
    val cookingTime: Int = 0
) : Serializable
