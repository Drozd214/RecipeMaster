package com.oleksandrkarpiuk.recipemaster.mapping.recipe

import com.oleksandrkarpiuk.recipemaster.api.models.RecipeDomainModel
import com.oleksandrkarpiuk.recipemaster.database.models.RecipeDatabaseModel
import com.oleksandrkarpiuk.recipemaster.models.recipes.RecipeItemModel
import com.oleksandrkarpiuk.recipemaster.models.recipes.RecipeSingleModel
import kotlin.math.roundToInt

class RecipeMapper :
    RecipeDatabaseMapper,
    RecipeItemMapper,
    RecipeSingleMapper
{

    override fun mapFromDomainToDatabase(recipe: RecipeDomainModel): RecipeDatabaseModel {
        return RecipeDatabaseModel(
            id = recipe.id,
            name = recipe.title,
            score = recipe.spoonacularScore,
            cookingTime = recipe.readyInMinutes,
            servings = recipe.servings,
            pricePerServing = recipe.pricePerServing,
            imageUrl = recipe.imageUrl ?: "",
            summary = recipe.summary,
            cuisines = recipe.cuisines,
            mealTypes = recipe.dishTypes,
            diets = recipe.diets,
            instructions = recipe.analyzedInstructions,
            ingredients = recipe.extendedIngredients,
            isFavourite = false
        )
    }

    override fun mapFromSingleToDatabase(recipe: RecipeSingleModel): RecipeDatabaseModel {
        return RecipeDatabaseModel(
            id = recipe.id,
            name = recipe.name,
            score = recipe.score.toInt(),
            cookingTime = recipe.cookingTime.toInt(),
            servings = recipe.servings,
            pricePerServing = recipe.price / recipe.servings,
            imageUrl = recipe.imageUrl,
            summary = recipe.summary,
            cuisines = recipe.cuisines,
            mealTypes = recipe.mealTypes,
            diets = recipe.diets,
            instructions = recipe.instructions,
            ingredients = recipe.ingredients,
            isFavourite = recipe.isFavourite
        )
    }

    override fun mapFromDomainToItem(recipe: RecipeDomainModel): RecipeItemModel {
        return RecipeItemModel(
            imageUrl = recipe.imageUrl,
            name = recipe.title,
            id = recipe.id,
            score = "${recipe.spoonacularScore}%",
            servings = recipe.servings,
            cookingTime = calculateTimeFromMinutes(recipe.readyInMinutes),
        )
    }

    override fun mapFromDatabaseToSingle(recipe: RecipeDatabaseModel): RecipeSingleModel {
        return RecipeSingleModel(
            id = recipe.id,
            name = recipe.name,
            score = recipe.score,
            cookingTime = recipe.cookingTime,
            servings = recipe.servings,
            price = calculatePrice(recipe.pricePerServing, recipe.servings),
            imageUrl = recipe.imageUrl,
            summary = recipe.summary,
            cuisines = recipe.cuisines,
            mealTypes = recipe.mealTypes,
            diets = recipe.diets,
            instructions = recipe.instructions,
            ingredients = recipe.ingredients,
            isFavourite = recipe.isFavourite
        )
    }

    private fun calculateTimeFromMinutes(timeInMinutes: Int): String {
        val hours = timeInMinutes / 60
        val minutes = timeInMinutes % 60
        return "${if(hours == 0) 0 else hours}h ${minutes}m"
    }

    private fun calculatePrice(pricePerServing: Float, servings: Int): Float {
        return (servings * pricePerServing.roundToInt() / 100.0f)
    }

}