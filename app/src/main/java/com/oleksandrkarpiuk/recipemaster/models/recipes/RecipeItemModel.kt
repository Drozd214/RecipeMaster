package com.oleksandrkarpiuk.recipemaster.models.recipes

import java.io.Serializable

class RecipeItemModel(
    imageUrl: String?,
    name: String,
    val id: Int,
    val score: String,
    val servings: Int = 0,
    val cookingTime: String
) : BaseRecipeItem(imageUrl, name), Serializable
