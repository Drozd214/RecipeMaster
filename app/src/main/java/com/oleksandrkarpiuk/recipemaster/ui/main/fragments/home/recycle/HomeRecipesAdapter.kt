package com.oleksandrkarpiuk.recipemaster.ui.main.fragments.home.recycle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oleksandrkarpiuk.recipemaster.databinding.ItemHomeRecipeBinding
import com.oleksandrkarpiuk.recipemaster.databinding.ItemRecipeBinding
import com.oleksandrkarpiuk.recipemaster.models.BaseRecipeItem

class HomeRecipesAdapter(
    private var recipeItems: List<BaseRecipeItem>
) : RecyclerView.Adapter<HomeRecipesViewHolder>() {

    private lateinit var binding: ItemHomeRecipeBinding

    var onItemClicked: ((BaseRecipeItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecipesViewHolder {
        binding = ItemHomeRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeRecipesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeRecipesViewHolder, position: Int) {
        holder.bind(recipeItems[position], onItemClicked)
    }

    override fun getItemCount(): Int {
        return recipeItems.count()
    }

    fun changeItems(items: List<BaseRecipeItem>) {
        recipeItems = items
        this.notifyDataSetChanged()
    }
}