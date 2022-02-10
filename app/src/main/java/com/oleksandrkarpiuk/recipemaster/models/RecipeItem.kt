package com.oleksandrkarpiuk.recipemaster.models

import java.io.Serializable

class RecipeItem(
    imageUrl: String?,
    name: String,
    val id: Int,
    val score: Int = 0,
    val servings: Int = 0,
    val cookingTime: Int = 0
) : BaseRecipeItem(imageUrl, name, false), Serializable
