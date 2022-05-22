package com.oleksandrkarpiuk.recipemaster.di.modules.core.subcomponents.fragments

import dagger.Module

@Module(
    subcomponents = [
        HomeComponent::class,
        RecipesComponent::class,
        FavouritesComponent::class
    ]
)
object FragmentsModule