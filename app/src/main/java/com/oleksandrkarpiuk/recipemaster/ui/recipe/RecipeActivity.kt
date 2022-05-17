package com.oleksandrkarpiuk.recipemaster.ui.recipe

import android.content.Intent
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.oleksandrkarpiuk.recipemaster.R
import com.oleksandrkarpiuk.recipemaster.RecipeMasterApplication
import com.oleksandrkarpiuk.recipemaster.databinding.ActivityRecipeBinding
import com.oleksandrkarpiuk.recipemaster.models.recipes.RecipeSingleModel
import com.oleksandrkarpiuk.recipemaster.ui.base.BaseActivity
import javax.inject.Inject


class RecipeActivity : BaseActivity() {

    companion object;

    private lateinit var binding: ActivityRecipeBinding
    private lateinit var viewModel: RecipeViewModel
    @Inject lateinit var factory: RecipeViewModelFactory

    private var isAddedToFavourites = false

    private val titleLength = 35

    override fun inject() {
        super.inject()
        (applicationContext as RecipeMasterApplication).getComponent()
            .createRecipeComponent()
            .create(this)
            .inject(this)
    }

    override fun preInit() {
        super.preInit()
        binding = ActivityRecipeBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        viewModel = ViewModelProviders.of(this, factory).get(RecipeViewModel::class.java)
    }

    override fun initViews() {
        super.initViews()
        binding.addToFavouritesButton.setOnClickListener {
            viewModel.onFavouriteClicked()
        }
    }

    override fun initData() {
        super.initData()
        viewModel.downloadRecipe(extractExtras(intent).recipeId)
    }

    override fun initObservers() {
        super.initObservers()
        viewModel.recipe.observe(this, Observer { recipe ->
            title = getShortStringTitle(recipe.name)
            refreshRecipe(recipe)
        })

        viewModel.error.observe(this, Observer { errorText ->
            if(errorText.isNotEmpty()) Toast.makeText(baseContext, errorText, Toast.LENGTH_SHORT).show()
        })
    }

    private fun getShortStringTitle(title: String): String {
        return if(title.length <= titleLength) title
        else title.substring(0, titleLength) + "..."
    }

    private fun refreshRecipe(recipe: RecipeSingleModel) {
        downloadImage(recipe.imageUrl)
        initPoints(recipe.score, recipe.servings, recipe.cookingTime, recipe.price)
        initIsAddedToFavourite(recipe.isFavourite)
        binding.recipeContentScrolling.summary.initSummaryBlock(recipe.summary)
        binding.recipeContentScrolling.details.initDiets(recipe.diets)
        binding.recipeContentScrolling.details.initCuisines(recipe.cuisines)
        binding.recipeContentScrolling.details.initMealTypes(recipe.mealTypes)

        binding.recipeContentScrolling.details1.initDiets(recipe.diets)
        binding.recipeContentScrolling.details1.initCuisines(recipe.cuisines)
        binding.recipeContentScrolling.details1.initMealTypes(recipe.mealTypes)

        binding.recipeContentScrolling.details2.initDiets(recipe.diets)
        binding.recipeContentScrolling.details2.initCuisines(recipe.cuisines)
        binding.recipeContentScrolling.details2.initMealTypes(recipe.mealTypes)

        binding.recipeContentScrolling.details3.initDiets(recipe.diets)
        binding.recipeContentScrolling.details3.initCuisines(recipe.cuisines)
        binding.recipeContentScrolling.details3.initMealTypes(recipe.mealTypes)

        binding.recipeContentScrolling.details4.initDiets(recipe.diets)
        binding.recipeContentScrolling.details4.initCuisines(recipe.cuisines)
        binding.recipeContentScrolling.details4.initMealTypes(recipe.mealTypes)
    }

    private fun downloadImage(imageUrl: String?) = Glide.with(binding.imageView)
        .load(imageUrl ?: "")
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(binding.imageView)

    private fun initPoints(score: Int, servings: Int, cookingTime: Int, price: Float) {
        binding.scoreTextView.text = "$score%"
        binding.servingsTextView.text = "$servings"
        binding.timeTextView.text = calculateTimeFromMinutes(cookingTime)
        binding.priceTextView.text = price.toString()
    }

    private fun calculateTimeFromMinutes(timeInMinutes: Int): String {
        val hours = timeInMinutes / 60
        val minutes = timeInMinutes % 60
        return "${if(hours == 0) 0 else hours}h ${minutes}m"
    }

    private fun initIsAddedToFavourite(isFavourite: Boolean) {
        binding.addToFavouritesButton.setBackgroundResource(
            if(isFavourite) R.drawable.rounded_heart_active_button
            else R.drawable.rounded_heart_inactive_button
        )

        binding.addToFavouritesButton.setImageResource(
            if (isFavourite) R.drawable.icon_heart_active
            else R.drawable.icon_heart_inactive
        )
    }
}