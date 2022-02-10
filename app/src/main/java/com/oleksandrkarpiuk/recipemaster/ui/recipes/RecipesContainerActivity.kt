package com.oleksandrkarpiuk.recipemaster.ui.recipes

import android.view.LayoutInflater
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.oleksandrkarpiuk.recipemaster.RecipeMasterApplication
import com.oleksandrkarpiuk.recipemaster.databinding.ActivityRecipesContainerBinding
import com.oleksandrkarpiuk.recipemaster.ui.base.BaseActivity
import com.oleksandrkarpiuk.recipemaster.ui.recipes.fragments.RecipesFragment
import javax.inject.Inject

class RecipesContainerActivity : BaseActivity() {

    private lateinit var binding: ActivityRecipesContainerBinding
    private lateinit var viewModel: RecipesContainerViewModel
    @Inject lateinit var factory: RecipesContainerViewModelFactory

    private var isFragmentExist = false

    override fun inject() {
        (applicationContext as RecipeMasterApplication).getComponent()
            .createRecipesComponent()
            .create(this)
            .inject(this)
    }

    override fun preInit() {
        super.preInit()
        binding = ActivityRecipesContainerBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        viewModel = ViewModelProviders.of(this, factory).get(RecipesContainerViewModel::class.java)
    }

    override fun initData() {
        super.initData()
        //TODO via navigation component
        viewModel.changeTitle(intent.extras?.getSerializable("TITLE") as String)
        val tag = intent.extras?.getSerializable("TAG") as String
        viewModel.init(tag)
    }

    override fun initObservers() {
        super.initObservers()
        viewModel.recipes.observe(this, Observer {
            val fragment = RecipesFragment(it)
            val transaction = supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id,fragment)

            if(isFragmentExist) transaction.addToBackStack(null)
            else isFragmentExist = true
            transaction.commit()
        })

        viewModel.title.observe(this, Observer {
            title = it
        })
    }

}