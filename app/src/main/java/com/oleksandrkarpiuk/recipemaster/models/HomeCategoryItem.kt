package com.oleksandrkarpiuk.recipemaster.models

import com.oleksandrkarpiuk.recipemaster.models.recipes.BaseRecipeItem
import java.io.Serializable

class HomeCategoryItem(
    val name: String,
    val items: List<BaseRecipeItem>,
    val tag: String
) : Serializable
