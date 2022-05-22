package com.oleksandrkarpiuk.recipemaster.data.stores.recipe

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.oleksandrkarpiuk.recipemaster.database.daos.RecipeDao
import com.oleksandrkarpiuk.recipemaster.database.models.RecipeDatabaseModel

class RecipeDatabaseStoreImpl(
    private val recipeDao: RecipeDao
) : RecipeDatabaseStore {

    override suspend fun saveRecipeFromDomaine(recipe: RecipeDatabaseModel) {
        val databaseResult = getRecipeById(recipe.id)
        val recipeForSave = if(databaseResult is Ok)
            recipe.copy(isFavourite = databaseResult.value.isFavourite)
            else recipe
        recipeDao.insertRecipe(recipeForSave)
    }

    override suspend fun updateRecipeFromApp(recipe: RecipeDatabaseModel) {
        recipeDao.insertRecipe(recipe)
    }

    override suspend fun getRecipeById(id: Int): Result<RecipeDatabaseModel, Throwable> {
        val databaseRecipe = recipeDao.getRecipeById(id)
        return if(databaseRecipe == null) Err(Throwable("Something went wrong"))
        else Ok(databaseRecipe)
    }

    override fun getFavouriteRecipes(): Result<List<RecipeDatabaseModel>, Throwable> {
        val databaseFavouriteRecipes = recipeDao.getFavouriteRecipes()
        return if(databaseFavouriteRecipes == null) Err(Throwable("Something went wrong"))
        else Ok(databaseFavouriteRecipes)
    }

}