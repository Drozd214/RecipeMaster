package com.oleksandrkarpiuk.recipemaster.ui.recipes

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.oleksandrkarpiuk.recipemaster.RecipeMasterApplication
import com.oleksandrkarpiuk.recipemaster.databinding.ActivityRecipesContainerBinding
import com.oleksandrkarpiuk.recipemaster.ui.base.BaseActivity
import com.oleksandrkarpiuk.recipemaster.ui.recipes.fragments.RecipesFragment
import javax.inject.Inject

class RecipesContainerActivity : BaseActivity() {

    companion object;

    private lateinit var binding: ActivityRecipesContainerBinding
    private lateinit var viewModel: RecipesContainerViewModel
    @Inject lateinit var factory: RecipesContainerViewModelFactory

    private var isFragmentExist = false
    private var previousTitle = ""

    override fun inject() {
        (applicationContext as RecipeMasterApplication).getComponent()
            .createRecipesContainerComponent()
            .create(this)
            .inject(this)
    }

    override fun preInit() {
        super.preInit()
        binding = ActivityRecipesContainerBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        viewModel = ViewModelProviders.of(this, factory).get(RecipesContainerViewModel::class.java)
        actionBar?.setDisplayHomeAsUpEnabled(true);
    }

    override fun initData() {
        super.initData()
        //TODO via navigation component
        val extras = extractExtras(intent)
        viewModel.refreshRecipes(extras.title, extras.tag)
    }

    override fun initObservers() {
        super.initObservers()
        viewModel.recipes.observe(this, Observer {
            val transaction = supportFragmentManager.beginTransaction()
            if(isFragmentExist) transaction.addToBackStack(null)
            else isFragmentExist = true
            transaction.replace(binding.fragmentContainer.id,RecipesFragment(it)).commit()
        })

        viewModel.title.observe(this, Observer {
            if(previousTitle.isEmpty()) previousTitle = it
            title = it
        })

        viewModel.inLoading.observe(this, Observer { inLoading ->
            if(inLoading) binding.progressBar.visibility = View.VISIBLE
            else binding.progressBar.visibility = View.GONE
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        title = previousTitle
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if(item.itemId == android.R.id.home) {
            onBackPressed()
            true
        } else super.onOptionsItemSelected(item)
    }

}