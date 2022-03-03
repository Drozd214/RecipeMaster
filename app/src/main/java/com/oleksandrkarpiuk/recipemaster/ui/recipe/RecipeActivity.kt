package com.oleksandrkarpiuk.recipemaster.ui.recipe

import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.oleksandrkarpiuk.recipemaster.R
import com.oleksandrkarpiuk.recipemaster.RecipeMasterApplication
import com.oleksandrkarpiuk.recipemaster.api.models.RecipeDomainModel
import com.oleksandrkarpiuk.recipemaster.databinding.ActivityRecipeBinding
import com.oleksandrkarpiuk.recipemaster.models.recipes.RecipeSingleModel
import com.oleksandrkarpiuk.recipemaster.ui.base.BaseActivity
import com.oleksandrkarpiuk.recipemaster.ui.recipe.customview.SummaryCustomView
import com.oleksandrkarpiuk.recipemaster.utils.AnimationStub
import javax.inject.Inject
import kotlin.math.roundToInt

class RecipeActivity : BaseActivity() {

    companion object;

    private lateinit var binding: ActivityRecipeBinding
    private lateinit var viewModel: RecipeViewModel
    @Inject lateinit var factory: RecipeViewModelFactory

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
        binding.summary.initSummaryBlock(recipe.summary)
        binding.details.initDiets(recipe.diets)
        binding.details.initCuisines(recipe.cuisines)
        binding.details.initMealTypes(recipe.mealTypes)
    }

    private fun downloadImage(imageUrl: String?) = Glide.with(binding.imageView)
        .load(imageUrl ?: "")
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(binding.imageView)

    private fun initPoints(score: String, servings: Int, cookingTime: String, price: Float) {
        binding.scoreTextView.text = score
        binding.servingsTextView.text = "$servings"
        binding.timeTextView.text = cookingTime
        binding.priceTextView.text = price.toString()
    }
}