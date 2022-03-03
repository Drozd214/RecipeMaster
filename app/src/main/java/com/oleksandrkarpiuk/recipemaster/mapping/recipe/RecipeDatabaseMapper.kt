package com.oleksandrkarpiuk.recipemaster.mapping.recipe

import com.oleksandrkarpiuk.recipemaster.api.models.RecipeDomainModel
import com.oleksandrkarpiuk.recipemaster.database.models.RecipeDatabaseModel
import com.oleksandrkarpiuk.recipemaster.models.recipes.RecipeSingleModel

interface RecipeDatabaseMapper {

    fun mapFromDomainToDatabase(recipe: RecipeDomainModel): RecipeDatabaseModel

}