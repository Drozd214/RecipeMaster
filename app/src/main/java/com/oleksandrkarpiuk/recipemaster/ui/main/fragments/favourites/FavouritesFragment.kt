package com.oleksandrkarpiuk.recipemaster.ui.main.fragments.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.oleksandrkarpiuk.recipemaster.RecipeMasterApplication
import com.oleksandrkarpiuk.recipemaster.databinding.FragmentFavouritesBinding
import com.oleksandrkarpiuk.recipemaster.ui.base.BaseFragment
import javax.inject.Inject

class FavouritesFragment : BaseFragment() {

    private lateinit var binding: FragmentFavouritesBinding
    private lateinit var viewModel: FavouritesViewModel
    @Inject lateinit var factory: FavouritesViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun inject() {
        (requireContext().applicationContext as RecipeMasterApplication)
            .getComponent()
            .createFavouritesComponent()
            .create(this)
            .inject(this)
    }

    override fun preInit() {
        super.preInit()
        viewModel = ViewModelProviders.of(this, factory).get(FavouritesViewModel::class.java)
    }

    override fun initData() {
        super.initData()
        viewModel.loadData()
    }

}