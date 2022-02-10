package com.oleksandrkarpiuk.recipemaster.ui.recipes.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.oleksandrkarpiuk.recipemaster.RecipeActivity
import com.oleksandrkarpiuk.recipemaster.RecipeMasterApplication
import com.oleksandrkarpiuk.recipemaster.databinding.FragmentRecipesBinding
import com.oleksandrkarpiuk.recipemaster.models.BaseRecipeItem
import com.oleksandrkarpiuk.recipemaster.models.CategoryItem
import com.oleksandrkarpiuk.recipemaster.models.RecipeItem
import com.oleksandrkarpiuk.recipemaster.ui.base.BaseFragment
import com.oleksandrkarpiuk.recipemaster.ui.recipes.RecipesContainerViewModel
import com.oleksandrkarpiuk.recipemaster.ui.recipes.recycle.RecipesAdapter
import javax.inject.Inject

class RecipesFragment(
    private val recipes: List<BaseRecipeItem>
) : BaseFragment() {

    private val parentViewModel: RecipesContainerViewModel by activityViewModels()
    private val columnNum = 2

    private lateinit var binding: FragmentRecipesBinding
    private lateinit var viewModel: RecipesViewModel
    @Inject lateinit var factory: RecipesViewModelFactory

    private lateinit var title: String
    private lateinit var recipesAdapter: RecipesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun inject() {
        (requireContext().applicationContext as RecipeMasterApplication)
            .getComponent()
            .createRecipesComponent()
            .create(this)
            .inject(this)
    }

    override fun preInit() {
        super.preInit()
        viewModel = ViewModelProviders.of(this, factory).get(RecipesViewModel::class.java)
    }

    override fun initViews() {
        super.initViews()
        initRecycleView()
        title = parentViewModel.title.value ?: ""
    }

    private fun initRecycleView() = with(binding.recipesRecycleView) {
        layoutManager = GridLayoutManager(context, columnNum)
        adapter = RecipesAdapter(recipes).apply {
            onItemClicked = { baseRecipeItem ->
                when(baseRecipeItem) {
                    is RecipeItem -> {
                        startActivity(Intent(context, RecipeActivity::class.java).apply {
                            putExtra("tagOrId", baseRecipeItem.id.toString())
                        })
                    }
                    is CategoryItem -> parentViewModel.refreshRecipes(baseRecipeItem.name, baseRecipeItem.tag)
                    else -> { }
                }
            }
        }.also {
            recipesAdapter = it
        }
        val horizontalItemDecorator = DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL)
        val verticalItemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        horizontalItemDecorator.setDrawable(ContextCompat.getDrawable(context, com.oleksandrkarpiuk.recipemaster.R.drawable.divider_recipes)!!)
        verticalItemDecorator.setDrawable(ContextCompat.getDrawable(context, com.oleksandrkarpiuk.recipemaster.R.drawable.divider_recipes)!!)
        addItemDecoration(horizontalItemDecorator)
        addItemDecoration(verticalItemDecorator)
    }

    override fun onResume() {
        super.onResume()
        parentViewModel.changeTitle(title)
    }

}