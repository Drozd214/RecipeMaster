package com.oleksandrkarpiuk.recipemaster.data.stores.recipe

import com.github.michaelbull.result.Result
import com.oleksandrkarpiuk.recipemaster.api.models.RecipeDomainModel

interface RecipeRemoteStore {

    suspend fun getRandomRecipes(number: Int, tags: String): Result<List<RecipeDomainModel>, Throwable>

}