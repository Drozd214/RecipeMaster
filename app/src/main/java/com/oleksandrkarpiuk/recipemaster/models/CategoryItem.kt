package com.oleksandrkarpiuk.recipemaster.models

import java.io.Serializable

class CategoryItem(
    imageUrl: String?,
    name: String,
    val tag: String
) : BaseRecipeItem(imageUrl, name, true), Serializable