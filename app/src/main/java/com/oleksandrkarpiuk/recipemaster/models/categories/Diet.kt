package com.oleksandrkarpiuk.recipemaster.models.categories

import com.oleksandrkarpiuk.recipemaster.R
import com.oleksandrkarpiuk.recipemaster.utils.DietRecipesImage
import com.oleksandrkarpiuk.recipemaster.utils.SpoonacularTags

enum class Diet(
    override val titleId: Int,
    override val tag: String,
    override val imageUrl: String,
    val isVisibleInCategory: Boolean
) : BaseCategory {

    DAIRY_FREE(R.string.dairy_free, SpoonacularTags.DAIRY_FREE, DietRecipesImage.getDairyFreeImage(), true),
    GLUTEN_FREE(R.string.gluten_free, SpoonacularTags.GLUTEN_FREE, DietRecipesImage.getGlutenFreeImage(), true),
    KETOGENIC(R.string.ketogenic, SpoonacularTags.KETOGENIC, DietRecipesImage.getKetogenicImage(), true),
    LACTO_OVO_VEGETARIAN(R.string.lacto_ovo_vegetarian, SpoonacularTags.LACTO_OVO_VEGETARIAN, DietRecipesImage.getLactoOvoVegetarianImage(), true),
    PALEOLITHIC(R.string.paleolithic, SpoonacularTags.PALEOLITHIC, DietRecipesImage.getPaleolithicImage(), false),
    PESCATARIAN(R.string.pescatarian, SpoonacularTags.PESCATARIAN, DietRecipesImage.getPescatarianImage(), false),
    PRIMAL(R.string.primal, SpoonacularTags.PRIMAL, DietRecipesImage.getPrimalImage(), false),
    VEGAN(R.string.vegan, SpoonacularTags.VEGAN, DietRecipesImage.getVeganImage(), false),
    VEGETARIAN(R.string.vegetarian, SpoonacularTags.VEGETARIAN, DietRecipesImage.getVegetarianImage(), true);

}