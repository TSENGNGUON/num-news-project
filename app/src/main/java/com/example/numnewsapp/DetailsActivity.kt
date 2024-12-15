package com.example.numnewsapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_details)


        // Find button view
        val btnBack: ImageView = findViewById(R.id.arrow_back)
        btnBack.setOnClickListener{
            onBackPressed()
        }

        // Retrieve data passed via Intent
        val title = intent.getStringExtra("title") ?: "No Title"
        val content = intent.getStringExtra("content") ?: "No content"
        val imageUrl = intent.getStringExtra("imageUrl")

        // Set data to views
        findViewById<TextView>(R.id.titleTv).text = title
        findViewById<TextView>(R.id.contentTv).text = content
        imageUrl?.let{
            val resourceId = resources.getIdentifier(it, "drawable", packageName)
            if(resourceId != 0){
                findViewById<ImageView>(R.id.imageViewimg).setImageResource(resourceId)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}