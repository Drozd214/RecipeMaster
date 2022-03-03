package com.oleksandrkarpiuk.recipemaster.ui.recipe.customview

import android.content.Context
import android.text.*
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.constraintlayout.widget.ConstraintLayout
import com.oleksandrkarpiuk.recipemaster.R
import com.oleksandrkarpiuk.recipemaster.databinding.ViewRecipeDetailsBinding
import com.oleksandrkarpiuk.recipemaster.utils.AlignSuperScript
import com.oleksandrkarpiuk.recipemaster.utils.AnimationStub
import com.oleksandrkarpiuk.recipemaster.utils.Constants

class DetailsCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var shortAnimationDuration: Long = Constants.SINGLE_RECIPE_ANIMATION_DURATION
    private var slideDownAnimation: Animation = AnimationUtils.loadAnimation(context, R.anim.slide_down).apply { duration = shortAnimationDuration }
    private var slideUpAnimation: Animation = AnimationUtils.loadAnimation(context, R.anim.slide_up).apply { duration = shortAnimationDuration }
    private var binding: ViewRecipeDetailsBinding = ViewRecipeDetailsBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        binding.detailsArrow.setOnClickListener {
            if(binding.detailsBlock.visibility == View.VISIBLE) {
                showSlideUpAnimation()
            } else {
                showSlideDownAnimation()
            }
        }
    }

    private fun showSlideUpAnimation() {
        binding.detailsArrow.rotation = 180f
        binding.detailsBlock.startAnimation(slideUpAnimation.apply {
            setAnimationListener(object: AnimationStub {
                override fun onAnimationEnd(p0: Animation?) {
                    binding.detailsBlock.visibility = View.GONE
                }
            })
        })
    }

    private fun showSlideDownAnimation() {
        binding.detailsArrow.rotation = 0f
        binding.detailsBlock.startAnimation(slideDownAnimation.apply {
            setAnimationListener(object: AnimationStub {
                override fun onAnimationStart(p0: Animation?) {
                    binding.detailsBlock.visibility = View.VISIBLE
                }
            })
        })
    }

    fun initDiets(diets: List<String>) {
        if(diets.isNullOrEmpty()) {
            binding.dietsTitle.visibility = View.GONE
            binding.dietsListTextView.visibility = View.GONE
        }
        else {
            binding.root.visibility = View.VISIBLE
            binding.dietsListTextView.visibility = View.VISIBLE
            binding.dietsListTextView.text = getItemsList(diets)
        }
    }

    fun initCuisines(cuisines: List<String>) {
        if(cuisines.isNullOrEmpty()) {
            binding.cuisinesTitle.visibility = View.GONE
            binding.cuisinesListTextView.visibility = View.GONE
        }
        else {
            binding.root.visibility = View.VISIBLE
            binding.cuisinesListTextView.visibility = View.VISIBLE
            binding.cuisinesListTextView.text = getItemsList(cuisines)
        }
    }

    fun initMealTypes(mealTypes: List<String>) {
        if(mealTypes.isNullOrEmpty()) {
            binding.mealTypesTitle.visibility = View.GONE
            binding.mealTypesListTextView.visibility = View.GONE
        }
        else {
            binding.root.visibility = View.VISIBLE
            binding.mealTypesListTextView.visibility = View.VISIBLE
            binding.mealTypesListTextView.text = getItemsList(mealTypes)
        }
    }

    private fun getItemsList(items: List<String>): SpannableStringBuilder {
        val dietsList = SpannableStringBuilder()
        for(item in items) {
            val dietUpper = item.substring(0, 1).uppercase() + item.substring(1)
            val dietSpannable = SpannableString("⚪ $dietUpper ")
            dietSpannable.setSpan(AlignSuperScript(0.5f), 0, 1, 0)
            dietsList.append(dietSpannable)
        }
        return dietsList
    }

}