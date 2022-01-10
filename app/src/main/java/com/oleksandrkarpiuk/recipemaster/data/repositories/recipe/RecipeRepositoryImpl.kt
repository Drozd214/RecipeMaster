package com.oleksandrkarpiuk.recipemaster.data.repositories.recipe

import com.github.michaelbull.result.Result
import com.oleksandrkarpiuk.recipemaster.api.models.Recipe
import com.oleksandrkarpiuk.recipemaster.data.stores.recipe.RecipeRemoteStore
import com.oleksandrkarpiuk.recipemaster.data.stores.recipe.RecipeRemoteStoreImpl

class RecipeRepositoryImpl(
    private val recipeRemoteStore: RecipeRemoteStore
) : RecipeRepository {

    override suspend fun getRandomRecipes(number: Int, tags: String): Result<List<Recipe>, Throwable> {
        return recipeRemoteStore.getRandomRecipes(number, tags)
    }

}