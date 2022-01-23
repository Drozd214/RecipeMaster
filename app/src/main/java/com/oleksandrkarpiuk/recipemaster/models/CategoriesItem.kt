package com.oleksandrkarpiuk.recipemaster.models

data class CategoriesItem(
    val name: String,
    var items: List<RecipeItem>
)
