package com.oleksandrkarpiuk.recipemaster

import android.app.Application
import com.oleksandrkarpiuk.recipemaster.di.DaggerRecipeMasterAppComponent
import com.oleksandrkarpiuk.recipemaster.di.RecipeMasterAppComponent

class RecipeMasterApplication : Application() {

    private lateinit var appComponent: RecipeMasterAppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerRecipeMasterAppComponent.factory().create(applicationContext)
    }

    fun getComponent(): RecipeMasterAppComponent = appComponent

}