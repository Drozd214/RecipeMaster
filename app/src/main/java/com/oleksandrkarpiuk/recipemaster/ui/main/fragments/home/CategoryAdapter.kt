package com.oleksandrkarpiuk.recipemaster.ui.main.fragments.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oleksandrkarpiuk.recipemaster.databinding.ItemCategoryItemBinding
import com.oleksandrkarpiuk.recipemaster.models.CategoryItem

class CategoryAdapter(
    private val categoryItems: List<CategoryItem>
) : RecyclerView.Adapter<CategoryViewHolder>() {

    private lateinit var binding: ItemCategoryItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        binding = ItemCategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoryItems[position])
    }

    override fun getItemCount(): Int {
        return categoryItems.count()
    }

}