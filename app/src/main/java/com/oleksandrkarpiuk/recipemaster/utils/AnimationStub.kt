package com.oleksandrkarpiuk.recipemaster.utils

import android.view.animation.Animation

interface AnimationStub : Animation.AnimationListener {

    override fun onAnimationStart(p0: Animation?) {}
    override fun onAnimationRepeat(p0: Animation?) {}
    override fun onAnimationEnd(p0: Animation?) {}

}