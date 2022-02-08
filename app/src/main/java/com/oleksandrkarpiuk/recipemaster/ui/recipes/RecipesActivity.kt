package com.oleksandrkarpiuk.recipemaster.ui.recipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.oleksandrkarpiuk.recipemaster.RecipeMasterApplication
import com.oleksandrkarpiuk.recipemaster.databinding.ActivityRecipesBinding
import com.oleksandrkarpiuk.recipemaster.models.HomeCategoryItem
import com.oleksandrkarpiuk.recipemaster.ui.base.BaseActivity
import com.oleksandrkarpiuk.recipemaster.ui.recipes.recycle.RecipesAdapter
import javax.inject.Inject

class RecipesActivity : BaseActivity() {

    private lateinit var binding: ActivityRecipesBinding
    private lateinit var viewModel: RecipesViewModel
    @Inject lateinit var factory: RecipesViewModelFactory

    private lateinit var recipesAdapter: RecipesAdapter

    private val columnNum = 2

    override fun inject() {
        (applicationContext as RecipeMasterApplication).getComponent()
            .createRecipesComponent()
            .create(this)
            .inject(this)
    }

    override fun preInit() {
        super.preInit()
        binding = ActivityRecipesBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        viewModel = ViewModelProviders.of(this, factory).get(RecipesViewModel::class.java)
    }

    override fun initData() {
        super.initData()
        //TODO via navigation component
        val categoryItem = intent.extras?.getSerializable("Category") as HomeCategoryItem
        title = categoryItem.name
        viewModel.init(categoryItem)
    }

    override fun initViews() {
        super.initViews()
        initRecycleView()
    }

    private fun initRecycleView() = with(binding.recipesRecycleView) {
        layoutManager = GridLayoutManager(context, columnNum)
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

    override fun initObservers() {
        super.initObservers()
        viewModel.recipes.observe(this, Observer {
            recipesAdapter.recipes = it
            recipesAdapter.isCategory = viewModel.isCategory
            recipesAdapter.notifyDataSetChanged()
        })
    }

}