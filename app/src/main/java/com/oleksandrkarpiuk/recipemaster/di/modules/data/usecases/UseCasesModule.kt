package com.oleksandrkarpiuk.recipemaster.di.modules.data.usecases

import com.oleksandrkarpiuk.recipemaster.data.repositories.recipe.RecipeRepository
import com.oleksandrkarpiuk.recipemaster.usecases.recipe.GetFavouriteRecipesUseCase
import com.oleksandrkarpiuk.recipemaster.usecases.recipe.GetFavouriteRecipesUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
object UseCasesModule {

    @Provides
    fun provideGetFavouriteRecipesUseCase(
        recipeRepository: RecipeRepository
    ): GetFavouriteRecipesUseCase {
        return GetFavouriteRecipesUseCaseImpl(recipeRepository)
    }

}