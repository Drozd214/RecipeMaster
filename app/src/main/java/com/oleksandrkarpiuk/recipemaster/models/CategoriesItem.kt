package com.oleksandrkarpiuk.recipemaster.models

import android.os.Parcelable
import java.io.Serializable

data class CategoriesItem(
    val name: String,
    val items: List<RecipeItem>,
    val tag: String
) : Serializable
