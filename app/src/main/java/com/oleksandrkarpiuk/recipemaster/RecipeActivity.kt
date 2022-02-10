package com.oleksandrkarpiuk.recipemaster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class RecipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)
        val tagOrId = intent.getStringExtra("tagOrId")
        findViewById<TextView>(R.id.textView).text = tagOrId
    }
}