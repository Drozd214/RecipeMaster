package com.oleksandrkarpiuk.recipemaster.ui.main.fragments.home

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oleksandrkarpiuk.recipemaster.databinding.ItemCategoryItemBinding
import com.oleksandrkarpiuk.recipemaster.models.CategoryItem

class CategoryViewHolder(itemView: ItemCategoryItemBinding) : RecyclerView.ViewHolder(itemView.root) {

    private val imageView = itemView.imageView
    private val nameView = itemView.nameTextView

    fun bind(item: CategoryItem) {
        Glide.with(itemView).load(item.imageUrl).into(imageView)
        nameView.text = item.name
    }

}