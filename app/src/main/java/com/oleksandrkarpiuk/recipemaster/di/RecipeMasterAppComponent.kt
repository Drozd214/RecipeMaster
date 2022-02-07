package com.oleksandrkarpiuk.recipemaster.di

import android.content.Context
import com.oleksandrkarpiuk.recipemaster.RecipeMasterApplication
import com.oleksandrkarpiuk.recipemaster.di.modules.core.CoreModule
import com.oleksandrkarpiuk.recipemaster.di.modules.core.subcomponents.activities.MainComponent
import com.oleksandrkarpiuk.recipemaster.di.modules.core.subcomponents.activities.RecipesComponent
import com.oleksandrkarpiuk.recipemaster.di.modules.core.subcomponents.fragments.HomeComponent
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
    fun createRecipesComponent(): RecipesComponent.Factory

    fun createHomeComponent(): HomeComponent.Factory

}