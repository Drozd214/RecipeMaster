package com.oleksandrkarpiuk.recipemaster.api.models

import com.oleksandrkarpiuk.recipemaster.models.RecipeItem

fun Recipe.toRecipeItem() : RecipeItem {
    return RecipeItem(
        imageUrl = this.imageUrl,
        name = this.title,
        id = this.id,
        score = this.spoonacularScore,
        servings = this.servings,
        cookingTime = this.readyInMinutes
    )
}