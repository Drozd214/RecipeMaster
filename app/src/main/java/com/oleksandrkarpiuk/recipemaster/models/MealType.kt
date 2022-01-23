package com.oleksandrkarpiuk.recipemaster.models

import com.oleksandrkarpiuk.recipemaster.utils.MealTypeRecipesImage

enum class MealType(val title: String, val tag: String, val imageUrl: String, val isVisibleInCategory: Boolean) {

    BREAKFAST("Breakfasts", "breakfast", MealTypeRecipesImage.getBreakfastImage(), true),
    BRUNCH("Brunch", "brunch", MealTypeRecipesImage.getBrunchImage(), false),
    LUNCH("Lunches", "lunch", MealTypeRecipesImage.getLunchImage(), true),
    DINNER("Dinners", "dinner", MealTypeRecipesImage.getDinnerImage(), true),
    ANTIPASTI("Antipasti", "antipasti", MealTypeRecipesImage.getAntipastiImage(), false),
    APPETIZER("Appetizers", "appetizer", MealTypeRecipesImage.getAppetizerImage(), false),
    BREAD("Breads", "bread", MealTypeRecipesImage.getBreadImage(), false),
    DESSERT("Desserts","dessert", MealTypeRecipesImage.getDessertImage(), true),
    DRINK("Drinks", "drink", MealTypeRecipesImage.getDrinkImage(), true),
    MAIN_COURSE("Main Courses", "main course", MealTypeRecipesImage.getMainCourseImage(), false),
    MAIN_DISH("Main Dishes", "main dish", MealTypeRecipesImage.getMainDishImage(), true),
    SALAD("Salads", "salad", MealTypeRecipesImage.getSaladImage(), true),
    SAUCE("Sauces", "sauce", MealTypeRecipesImage.getSauceImage(), false),
    SIDE_DISH("Side dishes","side dish", MealTypeRecipesImage.getSideDishImage(), false),
    SNACK("Snack", "snack", MealTypeRecipesImage.getSnackImage(), false),
    SOUP("Soups", "soup", MealTypeRecipesImage.getSoupImage(), false),
    STARTER("Starter", "starter", MealTypeRecipesImage.getStarterImage(), false)

}