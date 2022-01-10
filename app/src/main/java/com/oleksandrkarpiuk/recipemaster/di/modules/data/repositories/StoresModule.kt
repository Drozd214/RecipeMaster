package com.oleksandrkarpiuk.recipemaster.di.modules.data.repositories

import com.oleksandrkarpiuk.recipemaster.api.recipes.randomrecipe.RandomRecipeApi
import com.oleksandrkarpiuk.recipemaster.data.stores.recipe.RecipeRemoteStore
import com.oleksandrkarpiuk.recipemaster.data.stores.recipe.RecipeRemoteStoreImpl
import dagger.Module
import dagger.Provides

@Module
object StoresModule {

    @Provides
    fun provideRecipeRemoteStore(
        randomRecipeApi: RandomRecipeApi
    ): RecipeRemoteStore {
        return RecipeRemoteStoreImpl(randomRecipeApi)
    }

}