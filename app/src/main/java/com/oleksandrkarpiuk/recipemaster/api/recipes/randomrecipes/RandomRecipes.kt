package com.oleksandrkarpiuk.recipemaster.api.recipes.randomrecipes

import com.oleksandrkarpiuk.recipemaster.api.models.RecipeDomainModel
import com.squareup.moshi.Json

data class RandomRecipes(
    @field:Json(name = "recipes") val recipes: List<RecipeDomainModel>
)