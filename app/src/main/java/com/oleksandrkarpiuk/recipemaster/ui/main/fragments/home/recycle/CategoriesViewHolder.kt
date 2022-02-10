package com.oleksandrkarpiuk.recipemaster.ui.main.fragments.home.recycle

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oleksandrkarpiuk.recipemaster.R
import com.oleksandrkarpiuk.recipemaster.databinding.ItemCategoryBinding
import com.oleksandrkarpiuk.recipemaster.models.BaseRecipeItem
import com.oleksandrkarpiuk.recipemaster.models.HomeCategoryItem

class CategoriesViewHolder(itemView: ItemCategoryBinding) : RecyclerView.ViewHolder(itemView.root) {

    private val nameView = itemView.nameTextView
    private val seeAllButton = itemView.seeAllButton
    private val itemsRecycleView = itemView.recycleView
    private lateinit var itemsAdapter: HomeRecipesAdapter

    fun init(
        itemCLickedListener: ((BaseRecipeItem) -> Unit)?
    ) {
        with(itemsRecycleView) {
            layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = HomeRecipesAdapter(listOf()).apply {
                onItemClicked = { baseRecipeItem ->
                    itemCLickedListener?.invoke(baseRecipeItem)
                }
            }.also {
                itemsAdapter = it
            }
        }
    }

    fun bind(
        item: HomeCategoryItem,
        seeAllClickedListener: ((HomeCategoryItem) -> Unit)?
    ) {
        nameView.text = item.name
        seeAllButton.setOnClickListener { seeAllClickedListener?.invoke(item) }
        itemsAdapter.changeItems(item.items)
    }

}