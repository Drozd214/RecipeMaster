package com.oleksandrkarpiuk.recipemaster.usecases.recipe

import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.getError
import com.oleksandrkarpiuk.recipemaster.data.repositories.recipe.RecipeRepository
import com.oleksandrkarpiuk.recipemaster.models.recipes.RecipeSingleModel
import io.reactivex.rxjava3.core.Single

class GetFavouriteRecipesUseCaseImpl(
    private val recipeRepository: RecipeRepository
): GetFavouriteRecipesUseCase {

    override fun execute(): Single<List<RecipeSingleModel>> {
        val result = recipeRepository.getFavouriteRecipes()
        return if(result is Ok) {
            Single.just(result.value)
        } else {
            Single.error(result.getError())
        }
    }

}