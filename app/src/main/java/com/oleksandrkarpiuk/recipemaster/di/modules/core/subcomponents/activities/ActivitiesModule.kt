package com.oleksandrkarpiuk.recipemaster.di.modules.core.subcomponents.activities

import dagger.Module

@Module(
    subcomponents = [
        MainComponent::class,
        RecipesContainerComponent::class,
        RecipeComponent::class
    ]
)
object ActivitiesModule