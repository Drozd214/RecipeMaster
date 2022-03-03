package com.oleksandrkarpiuk.recipemaster.di.modules.core.subcomponents.activities

import com.oleksandrkarpiuk.recipemaster.data.repositories.recipe.RecipeRepository
import com.oleksandrkarpiuk.recipemaster.mapping.recipe.RecipeItemMapper
import com.oleksandrkarpiuk.recipemaster.ui.recipes.RecipesContainerActivity
import com.oleksandrkarpiuk.recipemaster.ui.recipes.RecipesContainerViewModelFactory
import com.oleksandrkarpiuk.recipemaster.utils.StringProvider
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Subcomponent(modules = [RecipesContainerComponent.ComponentModule::class])
interface RecipesContainerComponent {

    fun inject(activity: RecipesContainerActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: RecipesContainerActivity): RecipesContainerComponent
    }

    @Module
    object ComponentModule {
        @Provides
        fun provideRecipesViewModelFactory(
            recipeRepository: RecipeRepository,
            stringProvider: StringProvider,
            recipeItemMapper: RecipeItemMapper
        ): RecipesContainerViewModelFactory {
            return RecipesContainerViewModelFactory(recipeRepository, stringProvider, recipeItemMapper)
        }
    }

}