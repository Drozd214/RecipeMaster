package com.oleksandrkarpiuk.recipemaster.models.categories

import android.content.Context
import com.oleksandrkarpiuk.recipemaster.R
import com.oleksandrkarpiuk.recipemaster.models.RecipeItem
import com.oleksandrkarpiuk.recipemaster.utils.MealTypeRecipesImage
import com.oleksandrkarpiuk.recipemaster.utils.SpoonacularTags

enum class MealType(
    override val titleId: Int,
    val tag: String,
    override val imageUrl: String,
    val isVisibleInCategory: Boolean
) : BaseCategory {

    BREAKFAST(R.string.breakfast, SpoonacularTags.BREAKFAST, MealTypeRecipesImage.getBreakfastImage(), true),
    BRUNCH(R.string.brunch, SpoonacularTags.BRUNCH, MealTypeRecipesImage.getBrunchImage(), false),
    LUNCH(R.string.lunch, SpoonacularTags.LUNCH, MealTypeRecipesImage.getLunchImage(), true),
    DINNER(R.string.dinner, SpoonacularTags.DINNER, MealTypeRecipesImage.getDinnerImage(), true),
    ANTIPASTI(R.string.antipasti, SpoonacularTags.ANTIPASTI, MealTypeRecipesImage.getAntipastiImage(), false),
    APPETIZER(R.string.appetizer, SpoonacularTags.APPETIZER, MealTypeRecipesImage.getAppetizerImage(), false),
    BREAD(R.string.bread, SpoonacularTags.BREAD, MealTypeRecipesImage.getBreadImage(), false),
    DESSERT(R.string.dessert,SpoonacularTags.DESSERT, MealTypeRecipesImage.getDessertImage(), true),
    DRINK(R.string.drink, SpoonacularTags.DRINK, MealTypeRecipesImage.getDrinkImage(), true),
    MAIN_COURSE(R.string.main_course, SpoonacularTags.MAIN_COURSE, MealTypeRecipesImage.getMainCourseImage(), false),
    MAIN_DISH(R.string.main_dish, SpoonacularTags.MAIN_DISH, MealTypeRecipesImage.getMainDishImage(), true),
    SALAD(R.string.salad, SpoonacularTags.SALAD, MealTypeRecipesImage.getSaladImage(), true),
    SAUCE(R.string.sauce, SpoonacularTags.SAUCE, MealTypeRecipesImage.getSauceImage(), false),
    SIDE_DISH(R.string.side_dish,SpoonacularTags.SIDE_DISH, MealTypeRecipesImage.getSideDishImage(), false),
    SNACK(R.string.snack, SpoonacularTags.SNACK, MealTypeRecipesImage.getSnackImage(), false),
    SOUP(R.string.soup, SpoonacularTags.SOUP, MealTypeRecipesImage.getSoupImage(), false),
    STARTER(R.string.starter, SpoonacularTags.STARTER, MealTypeRecipesImage.getStarterImage(), false);

}