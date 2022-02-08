package com.oleksandrkarpiuk.recipemaster.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        preInit()
        init()
    }

    open fun inject() {}
    open fun preInit() {}
    open fun init() {
        initData()
        initViews()
        initObservers()
    }

    open fun initData() {}
    open fun initViews() {}
    open fun initObservers() {}

}