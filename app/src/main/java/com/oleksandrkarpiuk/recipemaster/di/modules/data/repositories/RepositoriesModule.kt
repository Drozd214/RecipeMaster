package com.oleksandrkarpiuk.recipemaster.di.modules.data.repositories

import com.oleksandrkarpiuk.recipemaster.data.repositories.recipe.RecipeRepository
import com.oleksandrkarpiuk.recipemaster.data.repositories.recipe.RecipeRepositoryImpl
import com.oleksandrkarpiuk.recipemaster.data.stores.recipe.RecipeRemoteStore
import dagger.Module
import dagger.Provides

@Module
object RepositoriesModule {

    @Provides
    fun provideRecipeRepository(
        recipeRemoteStore: RecipeRemoteStore
    ): RecipeRepository {
        return RecipeRepositoryImpl(recipeRemoteStore)
    }

}