package com.oleksandrkarpiuk.recipemaster.di.modules.data.database

import androidx.room.RoomDatabase
import com.oleksandrkarpiuk.recipemaster.database.RecipeMasterDatabase
import com.oleksandrkarpiuk.recipemaster.database.daos.RecipeDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DaosModule {

    @Singleton
    @Provides
    fun provideRecipeDao(roomDatabase: RecipeMasterDatabase): RecipeDao {
        return roomDatabase.recipeDao
    }

}