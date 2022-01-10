package com.oleksandrkarpiuk.recipemaster.data.repositories.recipe

import com.github.michaelbull.result.Result
import com.oleksandrkarpiuk.recipemaster.api.models.Recipe

interface RecipeRepository {

    suspend fun getRandomRecipes(number: Int, tags: String): Result<List<Recipe>, Throwable>

}