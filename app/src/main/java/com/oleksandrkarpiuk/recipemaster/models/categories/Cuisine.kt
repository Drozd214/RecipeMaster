package com.oleksandrkarpiuk.recipemaster.models.categories

import com.oleksandrkarpiuk.recipemaster.R
import com.oleksandrkarpiuk.recipemaster.utils.CuisineRecipesImage
import com.oleksandrkarpiuk.recipemaster.utils.SpoonacularTags

enum class Cuisine(
    override val titleId: Int,
    override val tag: String,
    override val imageUrl: String,
    val isVisibleInCategory: Boolean
) : BaseCategory {

    AMERICAN(R.string.american, SpoonacularTags.AMERICAN, CuisineRecipesImage.getAmericanImage(), false),
    ASIAN(R.string.asian, SpoonacularTags.ASIAN, CuisineRecipesImage.getAsianImage(), true),
    BRITISH(R.string.british, SpoonacularTags.BRITISH, CuisineRecipesImage.getBritishImage(), false),
    CAJUN(R.string.cajun, SpoonacularTags.CAJUN, CuisineRecipesImage.getCajunImage(), false),
    CHINESE(R.string.chinese, SpoonacularTags.CHINESE, CuisineRecipesImage.getChineseImage(), false),
    EUROPEAN(R.string.european, SpoonacularTags.EUROPEAN, CuisineRecipesImage.getEuropeanImage(),true),
    FRENCH(R.string.french, SpoonacularTags.FRENCH, CuisineRecipesImage.getFrenchImage(), false),
    INDIAN(R.string.indian, SpoonacularTags.INDIAN, CuisineRecipesImage.getIndianImage(), true),
    IRISH(R.string.irish, SpoonacularTags.IRISH, CuisineRecipesImage.getIrishImage(), false),
    ITALIAN(R.string.italian, SpoonacularTags.ITALIAN, CuisineRecipesImage.getItalianImage(), true),
    MEDITERRANEAN(R.string.mediterranean, SpoonacularTags.MEDITERRANEAN, CuisineRecipesImage.getMediterraneanImage(), true),
    SOUTHERN(R.string.southern, SpoonacularTags.SOUTHERN, CuisineRecipesImage.getSouthernImage(), true);

}