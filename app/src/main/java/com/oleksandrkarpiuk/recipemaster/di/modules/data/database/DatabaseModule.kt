package com.oleksandrkarpiuk.recipemaster.di.modules.data.database

import android.content.Context
import androidx.room.Room
import com.oleksandrkarpiuk.recipemaster.database.RecipeMasterDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        DaosModule::class
    ]
)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideRecipeMasterDatabase(
        context: Context
    ): RecipeMasterDatabase {
        return Room.databaseBuilder(
            context,
            RecipeMasterDatabase::class.java,
            RecipeMasterDatabase.NAME
        ).apply {
            this.allowMainThreadQueries()
            this.fallbackToDestructiveMigration()
        }.build()
    }

}