package com.oleksandrkarpiuk.recipemaster.di.modules.core.subcomponents.activities

import com.oleksandrkarpiuk.recipemaster.data.repositories.recipe.RecipeRepository
import com.oleksandrkarpiuk.recipemaster.ui.recipes.RecipesActivity
import com.oleksandrkarpiuk.recipemaster.ui.recipes.RecipesViewModelFactory
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Subcomponent(modules = [RecipesComponent.ComponentModule::class])
interface RecipesComponent {

    fun inject(activity: RecipesActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: RecipesActivity): RecipesComponent
    }

    @Module
    object ComponentModule {
        @Provides
        fun provideRecipesViewModelFactory(recipeRepository: RecipeRepository): RecipesViewModelFactory {
            return RecipesViewModelFactory(recipeRepository)
        }
    }

}