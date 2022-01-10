package com.oleksandrkarpiuk.recipemaster.di.modules.core.subcomponents.activities

import com.oleksandrkarpiuk.recipemaster.data.repositories.recipe.RecipeRepository
import com.oleksandrkarpiuk.recipemaster.ui.main.MainActivity
import com.oleksandrkarpiuk.recipemaster.ui.main.MainViewModelFactory
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Subcomponent(modules = [MainComponent.ComponentModule::class])
interface MainComponent {

    fun inject(activity: MainActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: MainActivity): MainComponent
    }

    @Module
    object ComponentModule {
        @Provides
        fun provideMainViewModelFactory(recipeRepository: RecipeRepository): MainViewModelFactory {
            return MainViewModelFactory(recipeRepository)
        }
    }

}