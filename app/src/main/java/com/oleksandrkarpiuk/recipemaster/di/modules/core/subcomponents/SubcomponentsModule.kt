package com.oleksandrkarpiuk.recipemaster.di.modules.core.subcomponents

import com.oleksandrkarpiuk.recipemaster.di.modules.core.subcomponents.activities.ActivitiesModule
import com.oleksandrkarpiuk.recipemaster.di.modules.core.subcomponents.fragments.FragmentsModule
import dagger.Module

@Module(
    includes = [
        ActivitiesModule::class,
        FragmentsModule::class
    ]
)
object SubcomponentsModule