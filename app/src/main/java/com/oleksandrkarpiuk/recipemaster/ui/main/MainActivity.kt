package com.oleksandrkarpiuk.recipemaster.ui.main

import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.oleksandrkarpiuk.recipemaster.R
import com.oleksandrkarpiuk.recipemaster.RecipeMasterApplication
import com.oleksandrkarpiuk.recipemaster.databinding.ActivityMainBinding
import com.oleksandrkarpiuk.recipemaster.ui.base.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    @Inject lateinit var factory: MainViewModelFactory

    override fun inject() {
        super.inject()
        (applicationContext as RecipeMasterApplication)
            .getComponent()
            .createMainComponent()
            .create(this)
            .inject(this)
    }

    override fun preInit() {
        super.preInit()
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
    }

    override fun initViews() {
        super.initViews()
        val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment? ?: return
        val navController = host.navController
        setUpBottomNav(navController)
    }

    private fun setUpBottomNav(navController: NavController) {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        bottomNav?.setupWithNavController(navController)
    }

}