package com.oleksandrkarpiuk.recipemaster.api.recipes.randomrecipe

import com.oleksandrkarpiuk.recipemaster.api.models.Recipe
import com.squareup.moshi.Json


data class RandomRecipes(
    @field:Json(name = "recipes") val recipes: List<Recipe>
)