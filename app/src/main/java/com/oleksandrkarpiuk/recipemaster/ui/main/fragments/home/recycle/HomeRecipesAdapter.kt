package com.oleksandrkarpiuk.recipemaster.ui.main.fragments.home.recycle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oleksandrkarpiuk.recipemaster.databinding.ItemRecipeBinding
import com.oleksandrkarpiuk.recipemaster.models.RecipeItem

class HomeRecipesAdapter(
    private var recipeItems: List<RecipeItem>
) : RecyclerView.Adapter<HomeRecipesViewHolder>() {

    private lateinit var binding: ItemRecipeBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecipesViewHolder {
        binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeRecipesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeRecipesViewHolder, position: Int) {
        holder.bind(recipeItems[position])
    }

    override fun getItemCount(): Int {
        return recipeItems.count()
    }

    fun changeItems(items: List<RecipeItem>) {
        recipeItems = items
        this.notifyDataSetChanged()
    }
}