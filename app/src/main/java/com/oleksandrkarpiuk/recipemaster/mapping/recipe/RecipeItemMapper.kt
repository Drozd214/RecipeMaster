package com.oleksandrkarpiuk.recipemaster.mapping.recipe

import com.oleksandrkarpiuk.recipemaster.api.models.RecipeDomainModel
import com.oleksandrkarpiuk.recipemaster.models.recipes.RecipeItemModel

interface RecipeItemMapper {

    fun mapFromDomainToItem(recipe: RecipeDomainModel): RecipeItemModel
}