package com.oleksandrkarpiuk.recipemaster.data.stores.recipe

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.oleksandrkarpiuk.recipemaster.database.daos.RecipeDao
import com.oleksandrkarpiuk.recipemaster.database.models.RecipeDatabase

class RecipeDatabaseStoreImpl(
    private val recipeDao: RecipeDao
) : RecipeDatabaseStore {

    override suspend fun saveRecipe(recipe: RecipeDatabase) {
        recipeDao.insertRecipe(recipe)
    }

    override suspend fun getRecipeById(id: Int): Result<RecipeDatabase, Throwable> {
        val databaseRecipe = recipeDao.getRecipeById(id)
        return if(databaseRecipe == null) Err(Throwable("Something went wrong"))
        else Ok(databaseRecipe)
    }

}