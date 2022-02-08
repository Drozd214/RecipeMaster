package com.oleksandrkarpiuk.recipemaster.ui.recipes.recycle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oleksandrkarpiuk.recipemaster.databinding.ItemCategoryRecipeBinding
import com.oleksandrkarpiuk.recipemaster.models.RecipeItem

class RecipesAdapter(
    var recipes: List<RecipeItem>,
    var isCategory: Boolean
) : RecyclerView.Adapter<RecipesViewHolder>() {

    private lateinit var binding: ItemCategoryRecipeBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        binding = ItemCategoryRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        holder.bind(recipes[position], isCategory)
    }

    override fun getItemCount(): Int {
        return recipes.count()
    }
}