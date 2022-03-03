package com.oleksandrkarpiuk.recipemaster.ui.recipe.customview

import android.content.Context
import android.text.method.LinkMovementMethod
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.text.HtmlCompat
import com.oleksandrkarpiuk.recipemaster.R
import com.oleksandrkarpiuk.recipemaster.databinding.ViewRecipeSummaryBinding
import com.oleksandrkarpiuk.recipemaster.utils.AnimationStub
import com.oleksandrkarpiuk.recipemaster.utils.Constants.SINGLE_RECIPE_ANIMATION_DURATION


class SummaryCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var shortAnimationDuration: Long = SINGLE_RECIPE_ANIMATION_DURATION
    private var slideDownAnimation: Animation = AnimationUtils.loadAnimation(context, R.anim.slide_down).apply { duration = shortAnimationDuration }
    private var slideUpAnimation: Animation = AnimationUtils.loadAnimation(context, R.anim.slide_up).apply { duration = shortAnimationDuration }
    private var binding: ViewRecipeSummaryBinding = ViewRecipeSummaryBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        binding.summaryArrow.setOnClickListener {
            if(binding.summaryTextView.visibility == View.VISIBLE) {
                showSlideUpAnimation()
            } else {
                showSlideDownAnimation()
            }
        }
    }

    private fun showSlideUpAnimation() {
        binding.summaryArrow.rotation = 180f
        binding.summaryTextView.startAnimation(slideUpAnimation.apply {
            setAnimationListener(object: AnimationStub {
                override fun onAnimationEnd(p0: Animation?) {
                    binding.summaryTextView.visibility = View.GONE
                }
            })
        })
    }

    private fun showSlideDownAnimation() {
        binding.summaryArrow.rotation = 0f
        binding.summaryTextView.startAnimation(slideDownAnimation.apply {
            setAnimationListener(object: AnimationStub {
                override fun onAnimationStart(p0: Animation?) {
                    binding.summaryTextView.visibility = View.VISIBLE
                }
            })
        })
    }

    fun initSummaryBlock(summary: String) {
        if(summary.isNullOrEmpty()) binding.root.visibility = View.GONE
        else {
            binding.root.visibility = VISIBLE
            binding.summaryTextView.text = HtmlCompat.fromHtml(summary, HtmlCompat.FROM_HTML_MODE_LEGACY)
            binding.summaryTextView.movementMethod = LinkMovementMethod.getInstance()
        }
    }

}