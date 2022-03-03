package com.oleksandrkarpiuk.recipemaster.ui.main.fragments.home.recycle

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oleksandrkarpiuk.recipemaster.databinding.ItemCategoryBinding
import com.oleksandrkarpiuk.recipemaster.models.recipes.BaseRecipeItem
import com.oleksandrkarpiuk.recipemaster.models.HomeCategoryItem

class CategoriesViewHolder(itemView: ItemCategoryBinding) : RecyclerView.ViewHolder(itemView.root) {

    private val nameView = itemView.nameTextView
    private val seeAllButton = itemView.seeAllButton
    private val itemsRecycleView = itemView.recycleView
    private val progressBar = itemView.progressBar
    private lateinit var itemsAdapter: HomeRecipesAdapter

    fun init(
        itemCLickListener: ((BaseRecipeItem) -> Unit)?
    ) {
        with(itemsRecycleView) {
            layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = HomeRecipesAdapter(listOf()).apply {
                onItemClicked = { baseRecipeItem ->
                    itemCLickListener?.invoke(baseRecipeItem)
                }
            }.also {
                itemsAdapter = it
            }
        }
    }

    fun bind(
        item: HomeCategoryItem,
        seeAllClickListener: ((HomeCategoryItem) -> Unit)?
    ) {
        if(item.items.isNullOrEmpty()) progressBar.visibility = View.VISIBLE
        else progressBar.visibility = View.GONE
        nameView.text = item.name
        seeAllButton.setOnClickListener { seeAllClickListener?.invoke(item) }
        itemsAdapter.changeItems(item.items)
    }

}