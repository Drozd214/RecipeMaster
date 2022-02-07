package com.oleksandrkarpiuk.recipemaster.ui.recipes

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.oleksandrkarpiuk.recipemaster.R
import com.oleksandrkarpiuk.recipemaster.RecipeMasterApplication
import com.oleksandrkarpiuk.recipemaster.databinding.ActivityRecipesBinding
import com.oleksandrkarpiuk.recipemaster.models.CategoriesItem
import com.oleksandrkarpiuk.recipemaster.ui.main.MainViewModel
import com.oleksandrkarpiuk.recipemaster.ui.main.MainViewModelFactory
import com.oleksandrkarpiuk.recipemaster.ui.main.fragments.home.recycle.CategoriesAdapter
import com.oleksandrkarpiuk.recipemaster.ui.recipes.recycle.RecipesAdapter
import javax.inject.Inject

class RecipesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipesBinding
    private lateinit var viewModel: RecipesViewModel
    @Inject lateinit var factory: RecipesViewModelFactory

    private lateinit var recipesAdapter: RecipesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        binding = ActivityRecipesBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        val categoryItem = intent.extras?.getSerializable("Category") as CategoriesItem
        title = categoryItem.name
        viewModel = ViewModelProviders.of(this, factory).get(RecipesViewModel::class.java)
        viewModel.init(categoryItem)
        with(binding.recipesRecycleView) {
            layoutManager = GridLayoutManager(context, 2)
            adapter = RecipesAdapter(mutableListOf(), false).also {
                recipesAdapter = it
            }
            val horizontalItemDecorator = DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL)
            val verticalItemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            horizontalItemDecorator.setDrawable(ContextCompat.getDrawable(context, com.oleksandrkarpiuk.recipemaster.R.drawable.divider_recipes)!!)
            verticalItemDecorator.setDrawable(ContextCompat.getDrawable(context, com.oleksandrkarpiuk.recipemaster.R.drawable.divider_recipes)!!)
            addItemDecoration(horizontalItemDecorator)
            addItemDecoration(verticalItemDecorator)
        }

        viewModel.recipes.observe(this, Observer {
            recipesAdapter.recipes = it
            recipesAdapter.isCategory = viewModel.isCategory
            recipesAdapter.notifyDataSetChanged()
        })
    }

    private fun inject() {
        (applicationContext as RecipeMasterApplication).getComponent()
            .createRecipesComponent()
            .create(this)
            .inject(this)
    }


}