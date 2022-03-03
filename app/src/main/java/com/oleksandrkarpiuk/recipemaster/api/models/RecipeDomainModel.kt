package com.oleksandrkarpiuk.recipemaster.api.models

import com.squareup.moshi.Json

data class RecipeDomainModel(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "spoonacularScore") val spoonacularScore: Int,
    @field:Json(name = "readyInMinutes") val readyInMinutes: Int,
    @field:Json(name = "servings") val servings: Int,
    @field:Json(name = "pricePerServing") val pricePerServing: Float,
    @field:Json(name = "image") val imageUrl: String?,
    @field:Json(name = "summary") val summary: String,
    @field:Json(name = "cuisines") val cuisines: List<String>,
    @field:Json(name = "dishTypes") val dishTypes: List<String>,
    @field:Json(name = "diets") val diets: List<String>,
    @field:Json(name = "analyzedInstructions") val analyzedInstructions: List<Any>,
    @field:Json(name = "extendedIngredients") val extendedIngredients: List<Any>,
)