package com.oleksandrkarpiuk.recipemaster.di.modules.core

import android.content.Context
import com.oleksandrkarpiuk.recipemaster.di.modules.core.subcomponents.SubcomponentsModule
import com.oleksandrkarpiuk.recipemaster.mapping.recipe.RecipeDatabaseMapper
import com.oleksandrkarpiuk.recipemaster.mapping.recipe.RecipeItemMapper
import com.oleksandrkarpiuk.recipemaster.mapping.recipe.RecipeMapper
import com.oleksandrkarpiuk.recipemaster.mapping.recipe.RecipeSingleMapper
import com.oleksandrkarpiuk.recipemaster.utils.StringProvider
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        SubcomponentsModule::class
    ]
)
object CoreModule {

    @Provides
    fun provideStringProvider(context: Context): StringProvider {
        return StringProvider(context)
    }

    @Provides
    fun provideRecipeDatabaseMapper(): RecipeDatabaseMapper {
        return RecipeMapper()
    }

    @Provides
    fun provideRecipeItemMapper(): RecipeItemMapper {
        return RecipeMapper()
    }

    @Provides
    fun provideRecipeSingleMapper(): RecipeSingleMapper {
        return RecipeMapper()
    }

}