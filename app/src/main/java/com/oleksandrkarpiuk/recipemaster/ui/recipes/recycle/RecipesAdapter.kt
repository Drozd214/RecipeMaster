package com.oleksandrkarpiuk.recipemaster.ui.recipes.recycle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oleksandrkarpiuk.recipemaster.databinding.ItemRecipeBinding
import com.oleksandrkarpiuk.recipemaster.models.BaseRecipeItem
import com.oleksandrkarpiuk.recipemaster.models.RecipeItem
import com.oleksandrkarpiuk.recipemaster.models.categories.BaseCategory

class RecipesAdapter(
    var recipes: List<BaseRecipeItem>
) : RecyclerView.Adapter<RecipesViewHolder>() {

    private lateinit var binding: ItemRecipeBinding

    var onItemClicked: ((BaseRecipeItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        holder.bind(recipes[position], onItemClicked)
    }

    override fun getItemCount(): Int {
        return recipes.count()
    }
}