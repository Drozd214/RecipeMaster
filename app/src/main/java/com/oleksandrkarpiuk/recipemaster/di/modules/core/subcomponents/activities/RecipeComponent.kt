package com.oleksandrkarpiuk.recipemaster.di.modules.core.subcomponents.activities

import com.oleksandrkarpiuk.recipemaster.data.repositories.recipe.RecipeRepository
import com.oleksandrkarpiuk.recipemaster.mapping.recipe.RecipeSingleMapper
import com.oleksandrkarpiuk.recipemaster.ui.recipe.RecipeActivity
import com.oleksandrkarpiuk.recipemaster.ui.recipe.RecipeViewModelFactory
import com.oleksandrkarpiuk.recipemaster.utils.StringProvider
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Subcomponent(modules = [RecipeComponent.ComponentModule::class])
interface RecipeComponent {

    fun inject(activity: RecipeActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: RecipeActivity): RecipeComponent
    }

    @Module
    object ComponentModule {
        @Provides
        fun provideRecipeViewModelFactory(
            recipeRepository: RecipeRepository
        ): RecipeViewModelFactory {
            return RecipeViewModelFactory(recipeRepository)
        }
    }

}