package com.oleksandrkarpiuk.recipemaster.ui.main.fragments.home.recycle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oleksandrkarpiuk.recipemaster.databinding.ItemCategoryBinding
import com.oleksandrkarpiuk.recipemaster.models.HomeCategoryItem

class CategoriesAdapter(
    var categories: MutableList<HomeCategoryItem>
) : RecyclerView.Adapter<CategoriesViewHolder>() {

    var onSeeAllButtonCLicked: ((HomeCategoryItem) -> Unit)? = null

    private lateinit var binding: ItemCategoryBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoriesViewHolder(binding).apply { this.init() }
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(categories[position], onSeeAllButtonCLicked)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

}