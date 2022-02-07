package com.oleksandrkarpiuk.recipemaster.ui.recipes.recycle

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.oleksandrkarpiuk.recipemaster.databinding.ItemCategoryRecipeBinding
import com.oleksandrkarpiuk.recipemaster.models.RecipeItem

class RecipesViewHolder(itemView: ItemCategoryRecipeBinding) : RecyclerView.ViewHolder(itemView.root) {

    private val image = itemView.recipeImageView
    private val name = itemView.recipeNameTextView
    private val score = itemView.recipeScoreTextView
    private val servings = itemView.recipeServingsTextView
    private val time = itemView.recipeTimeTextView

    fun bind(item: RecipeItem, isCategory: Boolean) {
        Glide.with(image)
            .load(item.imageUrl)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(15)))
            .into(image)
        name.text = item.name
        if(isCategory) {
            score.visibility = View.GONE
            servings.visibility = View.GONE
            time.visibility = View.GONE
        } else {
            score.text = "${item.score}%"
            servings.text = "${item.servings}"
            time.text = getTime(item.cookingTime)
        }
    }

    private fun getTime(timeInMinutes: Int): String {
        val hours = timeInMinutes / 60
        val minutes = timeInMinutes % 60
        return "${if(hours == 0) 0 else hours}h ${minutes}m"
    }
}