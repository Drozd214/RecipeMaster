package com.oleksandrkarpiuk.recipemaster.di.modules.core

import com.oleksandrkarpiuk.recipemaster.di.modules.core.subcomponents.SubcomponentsModule
import dagger.Module

@Module(
    includes = [
        SubcomponentsModule::class
    ]
)
object CoreModule