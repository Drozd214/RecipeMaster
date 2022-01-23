package com.oleksandrkarpiuk.recipemaster.models

import com.oleksandrkarpiuk.recipemaster.utils.DietRecipesImage

enum class Diet(val title: String, val tag: String, val imageUrl: String, val isVisibleInCategory: Boolean) {

    DAIRY_FREE("Dairy free", "dairy free", DietRecipesImage.getDairyFreeImage(), true),
    GLUTEN_FREE("Gluten free", "gluten free", DietRecipesImage.getGlutenFreeImage(), true),
    KETOGENIC("Ketogenic", "ketogenic", DietRecipesImage.getKetogenicImage(), true),
    LACTO_OVO_VEGETARIAN("Lacto-ovo-vegetarian", "lacto ovo vegetarian", DietRecipesImage.getLactoOvoVegetarianImage(), true),
    PALEOLITHIC("Paleolithic", "paleolithic", DietRecipesImage.getPaleolithicImage(), false),
    PESCATARIAN("Pescatarian", "pescatarian", DietRecipesImage.getPescatarianImage(), false),
    PRIMAL("Primal", "primal", DietRecipesImage.getPrimalImage(), false),
    VEGAN("Vegan", "vegan", DietRecipesImage.getVeganImage(), false),
    VEGETARIAN("Vegetarian", "vegetarian", DietRecipesImage.getVegetarianImage(), true)

}