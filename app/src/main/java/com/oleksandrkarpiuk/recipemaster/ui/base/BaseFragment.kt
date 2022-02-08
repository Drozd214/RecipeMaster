package com.oleksandrkarpiuk.recipemaster.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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