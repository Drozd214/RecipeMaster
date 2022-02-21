package com.oleksandrkarpiuk.recipemaster.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.oleksandrkarpiuk.recipemaster.database.models.RecipeDatabase

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipe(recipe: RecipeDatabase)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipes(recipes: List<RecipeDatabase>)

    @Query("SELECT * FROM ${RecipeDatabase.TABLE_NAME} WHERE :id == id")
    fun getRecipeById(id: Int): RecipeDatabase?

}