package com.oleksandrkarpiuk.recipemaster.models

import com.oleksandrkarpiuk.recipemaster.utils.CuisineRecipesImage

enum class Cuisine(val title: String, val tag: String, val imageUrl: String, val isVisibleInCategory: Boolean) {

    AMERICAN("American", "american", CuisineRecipesImage.getAmericanImage(), false),
    ASIAN("Asian", "asian", CuisineRecipesImage.getAsianImage(), true),
    BRITISH("British", "british", CuisineRecipesImage.getBritishImage(), false),
    CAJUN("Cajun", "cajun", CuisineRecipesImage.getCajunImage(), false),
    CHINESE("Chinese", "chinese", CuisineRecipesImage.getChineseImage(), false),
    EUROPEAN("European", "european", CuisineRecipesImage.getEuropeanImage(),true),
    FRENCH("French", "french", CuisineRecipesImage.getFrenchImage(), false),
    INDIAN("Indian", "indian", CuisineRecipesImage.getIndianImage(), true),
    IRISH("Irish", "irish", CuisineRecipesImage.getIrishImage(), false),
    ITALIAN("Italian", "italian", CuisineRecipesImage.getItalianImage(), true),
    MEDITERRANEAN("Mediterranean", "mediterranean", CuisineRecipesImage.getMediterraneanImage(), true),
    SOUTHERN("Southern", "southern", CuisineRecipesImage.getSouthernImage(), true),

}