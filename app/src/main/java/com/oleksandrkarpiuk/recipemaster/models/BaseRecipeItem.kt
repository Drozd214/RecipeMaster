package com.oleksandrkarpiuk.recipemaster.models

import java.io.Serializable

open class BaseRecipeItem(
    open val imageUrl: String?,
    open val name: String,
    val isCategory: Boolean
) : Serializable