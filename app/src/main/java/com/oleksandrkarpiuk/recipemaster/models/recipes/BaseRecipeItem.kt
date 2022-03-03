package com.oleksandrkarpiuk.recipemaster.models.recipes

import java.io.Serializable

open class BaseRecipeItem(
    open val imageUrl: String?,
    open val name: String
) : Serializable