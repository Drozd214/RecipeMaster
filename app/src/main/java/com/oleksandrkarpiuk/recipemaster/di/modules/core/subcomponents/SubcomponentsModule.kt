package com.oleksandrkarpiuk.recipemaster.di.modules.core.subcomponents

import com.oleksandrkarpiuk.recipemaster.di.modules.core.subcomponents.activities.ActivitiesModule
import dagger.Module

@Module(
    includes = [
        ActivitiesModule::class
    ]
)
object SubcomponentsModule