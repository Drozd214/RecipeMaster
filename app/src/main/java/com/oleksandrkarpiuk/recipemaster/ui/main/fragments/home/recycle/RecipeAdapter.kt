package com.oleksandrkarpiuk.recipemaster.ui.main.fragments.home.recycle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oleksandrkarpiuk.recipemaster.databinding.ItemRecipeBinding
import com.oleksandrkarpiuk.recipemaster.models.RecipeItem

class RecipeAdapter(
    private val recipeItems: List<RecipeItem>
) : RecyclerView.Adapter<RecipeViewHolder>() {

    private lateinit var binding: ItemRecipeBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipeItems[position])
    }

    override fun getItemCount(): Int {
        return recipeItems.count()
    }

}