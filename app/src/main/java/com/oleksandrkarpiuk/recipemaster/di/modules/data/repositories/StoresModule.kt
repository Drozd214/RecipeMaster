package com.oleksandrkarpiuk.recipemaster.di.modules.data.repositories

import com.oleksandrkarpiuk.recipemaster.api.recipes.randomrecipes.RandomRecipesApi
import com.oleksandrkarpiuk.recipemaster.data.stores.recipe.RecipeDatabaseStore
import com.oleksandrkarpiuk.recipemaster.data.stores.recipe.RecipeDatabaseStoreImpl
import com.oleksandrkarpiuk.recipemaster.data.stores.recipe.RecipeRemoteStore
import com.oleksandrkarpiuk.recipemaster.data.stores.recipe.RecipeRemoteStoreImpl
import com.oleksandrkarpiuk.recipemaster.database.daos.RecipeDao
import dagger.Module
import dagger.Provides

@Module
object StoresModule {

    @Provides
    fun provideRecipeRemoteStore(
        randomRecipesApi: RandomRecipesApi
    ): RecipeRemoteStore {
        return RecipeRemoteStoreImpl(randomRecipesApi)
    }

    @Provides
    fun provideRecipeDatabaseStore(
        recipeDao: RecipeDao
    ): RecipeDatabaseStore {
        return RecipeDatabaseStoreImpl(recipeDao)
    }

}