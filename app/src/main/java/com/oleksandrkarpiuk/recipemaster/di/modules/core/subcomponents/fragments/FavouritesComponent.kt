package com.oleksandrkarpiuk.recipemaster.di.modules.core.subcomponents.fragments

import com.oleksandrkarpiuk.recipemaster.ui.main.fragments.favourites.FavouritesFragment
import com.oleksandrkarpiuk.recipemaster.ui.main.fragments.favourites.FavouritesViewModelFactory
import com.oleksandrkarpiuk.recipemaster.usecases.recipe.GetFavouriteRecipesUseCase
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Subcomponent(modules = [FavouritesComponent.ComponentModule::class])
interface FavouritesComponent {

    fun inject(fragment: FavouritesFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance fragment: FavouritesFragment): FavouritesComponent
    }

    @Module
    object ComponentModule {
        @Provides
        fun provideFavouritesViewModelFactory(
            getFavouriteRecipesUseCase: GetFavouriteRecipesUseCase
        ): FavouritesViewModelFactory {
            return FavouritesViewModelFactory(getFavouriteRecipesUseCase)
        }
    }

}