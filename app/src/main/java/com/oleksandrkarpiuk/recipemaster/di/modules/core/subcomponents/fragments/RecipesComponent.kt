package com.oleksandrkarpiuk.recipemaster.di.modules.core.subcomponents.fragments

import com.oleksandrkarpiuk.recipemaster.ui.recipes.fragments.RecipesFragment
import com.oleksandrkarpiuk.recipemaster.ui.recipes.fragments.RecipesViewModelFactory
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
        fun provideRecipesViewModelFactory(): RecipesViewModelFactory {
            return RecipesViewModelFactory()
        }
    }

}