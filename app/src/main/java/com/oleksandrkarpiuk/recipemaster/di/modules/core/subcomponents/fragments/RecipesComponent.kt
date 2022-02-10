package com.oleksandrkarpiuk.recipemaster.di.modules.core.subcomponents.fragments

import com.oleksandrkarpiuk.recipemaster.data.repositories.recipe.RecipeRepository
import com.oleksandrkarpiuk.recipemaster.ui.main.fragments.home.HomeViewModelFactory
import com.oleksandrkarpiuk.recipemaster.ui.recipes.fragments.RecipesFragment
import com.oleksandrkarpiuk.recipemaster.ui.recipes.fragments.RecipesViewModelFactory
import com.oleksandrkarpiuk.recipemaster.utils.StringProvider
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Subcomponent(modules = [RecipesComponent.ComponentModule::class])
interface RecipesComponent {

    fun inject(fragment: RecipesFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance fragment: RecipesFragment): RecipesComponent
    }

    @Module
    object ComponentModule {
        @Provides
        fun provideRecipesViewModelFactory(
            recipeRepository: RecipeRepository,
            stringProvider: StringProvider
        ): RecipesViewModelFactory {
            return RecipesViewModelFactory(recipeRepository, stringProvider)
        }
    }

}