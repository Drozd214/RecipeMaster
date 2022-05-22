package com.oleksandrkarpiuk.recipemaster.di

import android.content.Context
import com.oleksandrkarpiuk.recipemaster.di.modules.core.CoreModule
import com.oleksandrkarpiuk.recipemaster.di.modules.core.subcomponents.activities.MainComponent
import com.oleksandrkarpiuk.recipemaster.di.modules.core.subcomponents.activities.RecipeComponent
import com.oleksandrkarpiuk.recipemaster.di.modules.core.subcomponents.activities.RecipesContainerComponent
import com.oleksandrkarpiuk.recipemaster.di.modules.core.subcomponents.fragments.FavouritesComponent
import com.oleksandrkarpiuk.recipemaster.di.modules.core.subcomponents.fragments.HomeComponent
import com.oleksandrkarpiuk.recipemaster.di.modules.core.subcomponents.fragments.RecipesComponent
import com.oleksandrkarpiuk.recipemaster.di.modules.data.DataModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CoreModule::class,
        DataModule::class
    ]
)
interface RecipeMasterAppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): RecipeMasterAppComponent
    }

    fun createMainComponent(): MainComponent.Factory
    fun createRecipesContainerComponent(): RecipesContainerComponent.Factory
    fun createRecipeComponent(): RecipeComponent.Factory

    fun createHomeComponent(): HomeComponent.Factory
    fun createFavouritesComponent(): FavouritesComponent.Factory

    fun createRecipesComponent(): RecipesComponent.Factory

}