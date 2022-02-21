package com.oleksandrkarpiuk.recipemaster.mapping

import com.oleksandrkarpiuk.recipemaster.api.models.Recipe
import com.oleksandrkarpiuk.recipemaster.database.models.RecipeDatabase
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

fun Recipe.toDatabaseRecipe() : RecipeDatabase {
    return RecipeDatabase(
        id = this.id,
        name = this.title,
        score = this.spoonacularScore,
        cookingTime = this.readyInMinutes,
        servings = this.servings,
        imageUrl = this.imageUrl ?: "",
        summary = this.summary,
        cuisines = this.cuisines,
        mealTypes = this.dishTypes,
        diets = this.diets,
        instructions = this.analyzedInstructions,
        ingredients = this.extendedIngredients
    )
}

fun RecipeDatabase.toRecipe() : Recipe {
    return Recipe(
        id = this.id,
        title = this.name,
        spoonacularScore = this.score,
        readyInMinutes = this.cookingTime,
        servings = this.servings,
        imageUrl = this.imageUrl ?: "",
        summary = this.summary,
        cuisines = this.cuisines,
        dishTypes = this.mealTypes,
        diets = this.diets,
        analyzedInstructions = this.instructions,
        extendedIngredients = this.ingredients
    )

}