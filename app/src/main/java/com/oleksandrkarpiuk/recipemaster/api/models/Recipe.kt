package com.oleksandrkarpiuk.recipemaster.api.models

import com.squareup.moshi.Json

data class Recipe(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "vegetarian") val vegetarian: Boolean,
    @field:Json(name = "vegan") val vegan: Boolean,
    @field:Json(name = "glutenFree") val glutenFree: Boolean,
    @field:Json(name = "dairyFree") val dairyFree: Boolean,
    @field:Json(name = "veryHealthy") val veryHealthy: Boolean,

    @field:Json(name = "readyInMinutes") val readyInMinutes: Int,
    @field:Json(name = "image") val imageUrl: String?,
    @field:Json(name = "cuisines") val cuisines: List<String>,
    @field:Json(name = "dishTypes") val dishTypes: List<String>,
    @field:Json(name = "diets") val diets: List<String>,
    @field:Json(name = "instructions") val instructions: String,
    @field:Json(name = "analyzedInstructions") val analyzedInstructions: List<Any>,
    @field:Json(name = "extendedIngredients") val extendedIngredients: List<Any>,
)