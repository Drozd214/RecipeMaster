package com.oleksandrkarpiuk.recipemaster.api.recipes.randomrecipe

import com.oleksandrkarpiuk.recipemaster.api.SpoonacularApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomRecipeApi {

    @GET("recipes/random")
    suspend fun getRandomRecipe(
        @Query("apiKey") apiKey: String = SpoonacularApi.API_KEY,
        @Query("number") number: Int,
        @Query("tags") tags: String,
    ): Response<RandomRecipes>

}