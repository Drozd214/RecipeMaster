package com.oleksandrkarpiuk.recipemaster.di.modules.core.subcomponents.fragments

import com.oleksandrkarpiuk.recipemaster.data.repositories.recipe.RecipeRepository
import com.oleksandrkarpiuk.recipemaster.mapping.recipe.RecipeItemMapper
import com.oleksandrkarpiuk.recipemaster.mapping.recipe.RecipeMapper
import com.oleksandrkarpiuk.recipemaster.ui.main.fragments.home.HomeFragment
import com.oleksandrkarpiuk.recipemaster.ui.main.fragments.home.HomeViewModelFactory
import com.oleksandrkarpiuk.recipemaster.utils.StringProvider
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Subcomponent(modules = [HomeComponent.ComponentModule::class])
interface HomeComponent {

    fun inject(fragment: HomeFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance fragment: HomeFragment): HomeComponent
    }

    @Module
    object ComponentModule {
        @Provides
        fun provideHomeViewModelFactory(
            recipeRepository: RecipeRepository,
            stringProvider: StringProvider,
            recipeItemMapper: RecipeItemMapper
        ): HomeViewModelFactory {
            return HomeViewModelFactory(recipeRepository, stringProvider, recipeItemMapper)
        }
    }

}