package com.oleksandrkarpiuk.recipemaster.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.oleksandrkarpiuk.recipemaster.database.models.RecipeDatabaseModel

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipe(recipe: RecipeDatabaseModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipes(recipes: List<RecipeDatabaseModel>)

    @Query("SELECT * FROM ${RecipeDatabaseModel.TABLE_NAME} WHERE :id == id")
    fun getRecipeById(id: Int): RecipeDatabaseModel?

    @Query("SELECT * FROM ${RecipeDatabaseModel.TABLE_NAME} WHERE ${RecipeDatabaseModel.FAVOURITE} = 1")
    fun getFavouriteRecipes(): List<RecipeDatabaseModel>?

}