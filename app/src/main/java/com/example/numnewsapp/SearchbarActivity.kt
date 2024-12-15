package com.example.numnewsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.numnewsapp.databinding.ActivitySearchBinding

class SearchbarActivity:AppCompatActivity() {

    lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivitySearchBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.btnBack.setOnClickListener{
            val intent = Intent(this,HomePageActivity::class.java)
            startActivity(intent)
        }
    }
}