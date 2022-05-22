package com.oleksandrkarpiuk.recipemaster.usecases.recipe

import com.oleksandrkarpiuk.recipemaster.models.recipes.RecipeSingleModel
import io.reactivex.rxjava3.core.Single

interface GetFavouriteRecipesUseCase {

    fun execute(): Single<List<RecipeSingleModel>>

}