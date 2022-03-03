package com.oleksandrkarpiuk.recipemaster.models.recipes

import java.io.Serializable

class CategoryItem(
    imageUrl: String?,
    name: String,
    val tag: String
) : BaseRecipeItem(imageUrl, name), Serializable