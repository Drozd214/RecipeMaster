package com.oleksandrkarpiuk.recipemaster.ui.recipes.recycle

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.oleksandrkarpiuk.recipemaster.databinding.ItemRecipeBinding
import com.oleksandrkarpiuk.recipemaster.models.BaseRecipeItem
import com.oleksandrkarpiuk.recipemaster.models.RecipeItem

class RecipesViewHolder(itemView: ItemRecipeBinding) : RecyclerView.ViewHolder(itemView.root) {

    private val image = itemView.imageView
    private val name = itemView.nameTextView
    private val score = itemView.scoreTextView
    private val servings = itemView.servingsTextView
    private val time = itemView.timeTextView

    fun bind(
        item: BaseRecipeItem,
        itemClickListener: ((BaseRecipeItem) -> Unit)?
    ) {
        Glide.with(image)
            .load(item.imageUrl)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(15)))
            .into(image)
        name.text = item.name
        if(item is RecipeItem) {
            score.text = "${item.score}%"
            servings.text = "${item.servings}"
            time.text = getTime(item.cookingTime)
        } else {
            score.visibility = View.GONE
            servings.visibility = View.GONE
            time.visibility = View.GONE
        }

        itemView.setOnClickListener { itemClickListener?.invoke(item) }
    }

    private fun getTime(timeInMinutes: Int): String {
        val hours = timeInMinutes / 60
        val minutes = timeInMinutes % 60
        return "${if(hours == 0) 0 else hours}h ${minutes}m"
    }
}