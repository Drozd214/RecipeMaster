package com.oleksandrkarpiuk.recipemaster.ui.main.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.oleksandrkarpiuk.recipemaster.RecipeMasterApplication
import com.oleksandrkarpiuk.recipemaster.databinding.FragmentHomeBinding
import com.oleksandrkarpiuk.recipemaster.models.CategoriesItem
import com.oleksandrkarpiuk.recipemaster.models.RecipeItem
import javax.inject.Inject
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.oleksandrkarpiuk.recipemaster.api.models.toRecipeItem
import com.oleksandrkarpiuk.recipemaster.models.MealType
import com.oleksandrkarpiuk.recipemaster.ui.main.fragments.home.recycle.CategoriesAdapter

class HomeFragment() : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var viewModel: HomeViewModel
    @Inject lateinit var factory: HomeViewModelFactory

    private lateinit var categoriesAdapter: CategoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        inject()
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initData()
        initObservers()
    }

    private fun inject() {
        (requireContext().applicationContext as RecipeMasterApplication)
            .getComponent()
            .createHomeComponent()
            .create(this)
            .inject(this)
    }

    private fun initViews() {
        with(binding.categoriesRecycleView) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = CategoriesAdapter(mutableListOf()).also {
                categoriesAdapter = it
            }

            val itemDecorator = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            itemDecorator.setDrawable(ContextCompat.getDrawable(requireContext(), com.oleksandrkarpiuk.recipemaster.R.drawable.divider_categories)!!)
            addItemDecoration(itemDecorator)
        }
    }

    private fun initData() {
        viewModel = ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)
        viewModel.init()
    }

    private fun initObservers() {
        viewModel.categories.observe(this, Observer { categories ->
            categoriesAdapter.categories = categories.toMutableList()
            categoriesAdapter.notifyDataSetChanged()
        })

        viewModel.dairyFreeDietRecipes.observe(this, Observer { notifyItemChanges(it) })
        viewModel.primalDietRecipes.observe(this, Observer { notifyItemChanges(it) })
        viewModel.lunchRecipes.observe(this, Observer { notifyItemChanges(it) })
        viewModel.mainDishesRecipes.observe(this, Observer { notifyItemChanges(it) })
        viewModel.sideDishesRecipes.observe(this, Observer { notifyItemChanges(it) })
        viewModel.soupsRecipes.observe(this, Observer { notifyItemChanges(it) })
        viewModel.dessertsRecipes.observe(this, Observer { notifyItemChanges(it) })
        viewModel.chineseRecipes.observe(this, Observer { notifyItemChanges(it) })
        viewModel.frenchRecipes.observe(this, Observer { notifyItemChanges(it) })
        viewModel.italianRecipes.observe(this, Observer { notifyItemChanges(it) })
        viewModel.mediterraneanRecipes.observe(this, Observer { notifyItemChanges(it) })
        viewModel.southernRecipes.observe(this, Observer { notifyItemChanges(it) })

        viewModel.error.observe(this, Observer {
            if(it.isNotEmpty()) Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun notifyItemChanges(category: CategoriesItem) {
        val categoryPosition = categoriesAdapter.categories.indexOf(categoriesAdapter.categories.find { it.name == category.name})
        categoriesAdapter.categories[categoryPosition] = category
        categoriesAdapter.notifyItemChanged(categoryPosition)
    }

}