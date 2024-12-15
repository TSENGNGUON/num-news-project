package com.example.numnewsapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.*

@SuppressLint("CustomSplashScreen")
class  MainActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.splash_activity)

        firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser

        if (currentUser != null){
            val intent = Intent(this@MainActivity, HomePageActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            // Coroutine-based delay
            GlobalScope.launch(Dispatchers.Main) {
                delay(3000)
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                finish()
            }
        }

    }
}
