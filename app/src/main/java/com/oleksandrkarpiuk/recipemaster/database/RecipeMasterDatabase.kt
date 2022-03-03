package com.oleksandrkarpiuk.recipemaster.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.oleksandrkarpiuk.recipemaster.database.RecipeMasterDatabase.Companion.VERSION
import com.oleksandrkarpiuk.recipemaster.database.converters.Converters
import com.oleksandrkarpiuk.recipemaster.database.daos.RecipeDao
import com.oleksandrkarpiuk.recipemaster.database.models.RecipeDatabaseModel

@Database(
    entities = [
        RecipeDatabaseModel::class
    ], version = VERSION
)
@TypeConverters(Converters::class)
abstract class RecipeMasterDatabase : RoomDatabase() {

    companion object {
        const val NAME = "recipe_master_database.db"
        const val VERSION = 2
    }

    abstract val recipeDao: RecipeDao
}