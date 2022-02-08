package com.oleksandrkarpiuk.recipemaster.models

import java.io.Serializable

class HomeCategoryItem(
    val name: String,
    val items: List<RecipeItem>,
    val tag: String
) : Serializable
