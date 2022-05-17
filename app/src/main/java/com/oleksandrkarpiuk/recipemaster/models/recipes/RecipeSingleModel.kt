package com.oleksandrkarpiuk.recipemaster.models.recipes

data class RecipeSingleModel(
    val id: Int,
    val name: String,
    val score: Int,
    val cookingTime: Int,
    val servings: Int,
    val price: Float,
    val imageUrl: String,
    val summary: String,
    val cuisines: List<String>,
    val mealTypes: List<String>,
    val diets: List<String>,
    val instructions: List<Any>,
    val ingredients: List<Any>,
    val isFavourite: Boolean
)