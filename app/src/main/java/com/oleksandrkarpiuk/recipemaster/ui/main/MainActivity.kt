package com.oleksandrkarpiuk.recipemaster.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.oleksandrkarpiuk.recipemaster.R
import com.oleksandrkarpiuk.recipemaster.RecipeMasterApplication
import com.oleksandrkarpiuk.recipemaster.api.recipes.randomrecipe.RandomRecipeApi
import kotlinx.coroutines.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    @Inject lateinit var factory: MainViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)

        val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment? ?: return
        val navController = host.navController
        setUpBottomNav(navController)
    }

    private fun inject() {
        (applicationContext as RecipeMasterApplication)
            .getComponent()
            .createMainComponent()
            .create(this)
            .inject(this)
    }

    private fun setUpBottomNav(navController: NavController) {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        bottomNav?.setupWithNavController(navController)
    }

}