package com.oleksandrkarpiuk.recipemaster.ui.main.fragments.home.recycle

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.oleksandrkarpiuk.recipemaster.databinding.ItemHomeRecipeBinding
import com.oleksandrkarpiuk.recipemaster.databinding.ItemRecipeBinding
import com.oleksandrkarpiuk.recipemaster.models.BaseRecipeItem

class HomeRecipesViewHolder(itemView: ItemHomeRecipeBinding) : RecyclerView.ViewHolder(itemView.root) {

    private val imageView = itemView.imageView
    private val nameView = itemView.nameTextView

    fun bind(
        item: BaseRecipeItem,
        itemCLickListener: ((BaseRecipeItem) -> Unit)? = null
    ) {
        item.imageUrl?.let {
            Glide.with(itemView)
                .load(it)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(15)))
                .into(imageView)
        }
        nameView.text = item.name
        itemView.setOnClickListener { itemCLickListener?.invoke(item) }

    }

}