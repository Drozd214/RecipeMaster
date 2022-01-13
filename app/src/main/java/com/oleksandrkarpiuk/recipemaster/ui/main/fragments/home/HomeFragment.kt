package com.oleksandrkarpiuk.recipemaster.ui.main.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.oleksandrkarpiuk.recipemaster.RecipeMasterApplication
import com.oleksandrkarpiuk.recipemaster.databinding.FragmentHomeBinding
import com.oleksandrkarpiuk.recipemaster.models.CategoriesItem
import com.oleksandrkarpiuk.recipemaster.models.CategoryItem
import javax.inject.Inject

import androidx.core.content.ContextCompat

import androidx.recyclerview.widget.DividerItemDecoration




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
        viewModel = ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)

        val categoryItemsStub = mutableListOf<CategoryItem>().apply {
            add(CategoryItem("https://spoonacular.com/recipeImages/641227-556x370.jpg", "Dandelion pesto"))
            add(CategoryItem("https://spoonacular.com/recipeImages/662276-556x370.jpg","Sun Dried Tomato and Herb Baked Eggs"))
            add(CategoryItem("https://spoonacular.com/recipeImages/631748-556x370.jpg", "Asian Shrimp Stir-Fry"))
            add(CategoryItem("https://spoonacular.com/recipeImages/641227-556x370.jpg", "Dandelion pesto"))
            add(CategoryItem("https://spoonacular.com/recipeImages/662276-556x370.jpg","Sun Dried Tomato and Herb Baked Eggs"))
            add(CategoryItem("https://spoonacular.com/recipeImages/631748-556x370.jpg", "Asian Shrimp Stir-Fry"))
            add(CategoryItem("https://spoonacular.com/recipeImages/641227-556x370.jpg", "Dandelion pesto"))
            add(CategoryItem("https://spoonacular.com/recipeImages/662276-556x370.jpg","Sun Dried Tomato and Herb Baked Eggs"))
            add(CategoryItem("https://spoonacular.com/recipeImages/631748-556x370.jpg", "Asian Shrimp Stir-Fry"))
        }

        val categories = mutableListOf<CategoriesItem>().apply {
            add(CategoriesItem("Diets", categoryItemsStub))
            add(CategoriesItem("Cuisines", categoryItemsStub))
            add(CategoriesItem("Meal Types", categoryItemsStub))
            add(CategoriesItem("Breakfasts", categoryItemsStub))
            add(CategoriesItem("Side Dishes", categoryItemsStub))
            add(CategoriesItem("Desserts", categoryItemsStub))
            add(CategoriesItem("Soups", categoryItemsStub))
            add(CategoriesItem("Italian", categoryItemsStub))
            add(CategoriesItem("French", categoryItemsStub))
        }

        with(binding.categoriesRecycleView) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = CategoriesAdapter(categories).also {
                categoriesAdapter = it
            }

            val itemDecorator = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            itemDecorator.setDrawable(ContextCompat.getDrawable(requireContext(), com.oleksandrkarpiuk.recipemaster.R.drawable.divider_categories)!!)
            addItemDecoration(itemDecorator)
        }

    }

    private fun inject() {
        (requireContext().applicationContext as RecipeMasterApplication)
            .getComponent()
            .createHomeComponent()
            .create(this)
            .inject(this)
    }

}