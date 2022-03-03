package com.oleksandrkarpiuk.recipemaster.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.oleksandrkarpiuk.recipemaster.database.models.RecipeDatabaseModel.Companion.TABLE_NAME
import java.io.Serializable

@Entity(
    tableName = TABLE_NAME
)
data class RecipeDatabaseModel(
    @PrimaryKey @ColumnInfo(name = ID) val id: Int,
    @ColumnInfo(name = NAME) val name: String,
    @ColumnInfo(name = SPOONACULAR_SCORE) val score: Int,
    @ColumnInfo(name = COOKING_TIME) val cookingTime: Int,
    @ColumnInfo(name = SERVINGS) val servings: Int,
    @ColumnInfo(name = PRICE_PER_SERVING) val pricePerServing: Float,
    @ColumnInfo(name = IMAGE_URL) val imageUrl: String,
    @ColumnInfo(name = SUMMARY) val summary: String,
    @ColumnInfo(name = CUISINES) val cuisines: List<String>,
    @ColumnInfo(name = MEAL_TYPES) val mealTypes: List<String>,
    @ColumnInfo(name = DIETS) val diets: List<String>,
    @ColumnInfo(name = INSTRUCTIONS) val instructions: List<Any>,
    @ColumnInfo(name = INGREDIENTS) val ingredients: List<Any>,
) : Serializable {

    companion object {
        const val TABLE_NAME = "recipes"
        const val ID = "id"
        const val NAME = "name"
        const val SPOONACULAR_SCORE = "score"
        const val COOKING_TIME = "cooking_time"
        const val SERVINGS = "servings"
        const val PRICE_PER_SERVING = "price_per_serving"
        const val IMAGE_URL = "image_url"
        const val SUMMARY = "summary"
        const val CUISINES = "cuisines"
        const val MEAL_TYPES = "meal_types"
        const val DIETS = "diets"
        const val INSTRUCTIONS = "instructions"
        const val INGREDIENTS = "ingredients"
    }

}
