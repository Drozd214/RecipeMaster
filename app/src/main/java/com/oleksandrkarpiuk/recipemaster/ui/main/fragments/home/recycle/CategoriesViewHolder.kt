package com.oleksandrkarpiuk.recipemaster.ui.main.fragments.home.recycle

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oleksandrkarpiuk.recipemaster.databinding.ItemCategoryBinding
import com.oleksandrkarpiuk.recipemaster.models.CategoriesItem

class CategoriesViewHolder(itemView: ItemCategoryBinding) : RecyclerView.ViewHolder(itemView.root) {

    private val nameView = itemView.nameTextView
    private val seeAllButton = itemView.seeAllButton
    private val itemsRecycleView = itemView.recycleView

    fun bind(item: CategoriesItem) {
        nameView.text = item.name
        seeAllButton.text = "SEE ALL"
        with(itemsRecycleView) {
            layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = RecipeAdapter(item.items)
        }
    }

}