package com.oleksandrkarpiuk.recipemaster.di.modules.data

import com.oleksandrkarpiuk.recipemaster.di.modules.data.database.DatabaseModule
import com.oleksandrkarpiuk.recipemaster.di.modules.data.remote.RemoteModule
import com.oleksandrkarpiuk.recipemaster.di.modules.data.repositories.RepositoriesModule
import com.oleksandrkarpiuk.recipemaster.di.modules.data.repositories.StoresModule
import com.oleksandrkarpiuk.recipemaster.di.modules.data.usecases.UseCasesModule
import dagger.Module

@Module(
    includes = [
        RemoteModule::class,
        DatabaseModule::class,
        RepositoriesModule::class,
        StoresModule::class,
        UseCasesModule::class
    ]
)
object DataModule