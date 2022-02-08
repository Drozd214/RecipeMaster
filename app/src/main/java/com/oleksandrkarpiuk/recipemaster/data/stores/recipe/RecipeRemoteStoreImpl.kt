package com.oleksandrkarpiuk.recipemaster.data.stores.recipe

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.oleksandrkarpiuk.recipemaster.api.models.Recipe
import com.oleksandrkarpiuk.recipemaster.api.recipes.randomrecipes.RandomRecipesApi

class RecipeRemoteStoreImpl(
    private val randomRecipesApi: RandomRecipesApi
) : RecipeRemoteStore {

    override suspend fun getRandomRecipes(number: Int, tags: String): Result<List<Recipe>, Throwable> {
        val response = randomRecipesApi.getRandomRecipe(number = number, tags = tags)
        return if(response.isSuccessful) Ok(response.body()?.recipes ?: listOf())
        else Err(Throwable("Something went wrong"))
    }

}