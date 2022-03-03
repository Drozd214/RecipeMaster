package com.oleksandrkarpiuk.recipemaster.mapping.recipe

import com.oleksandrkarpiuk.recipemaster.database.models.RecipeDatabaseModel
import com.oleksandrkarpiuk.recipemaster.models.recipes.RecipeSingleModel

interface RecipeSingleMapper {

    fun mapFromDatabaseToSingle(recipe: RecipeDatabaseModel): RecipeSingleModel

}