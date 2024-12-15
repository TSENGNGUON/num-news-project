package com.example.numnewsapp


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.numnewsapp.databinding.HomePageBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomePageActivity: AppCompatActivity() {



    lateinit var binding:HomePageBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val homePageFragment = HomePageFragment()
        val exploreFragment = VideoFragment();
        val bookMarkFragment = BookMarkFragment();
        val profileFragment = ProfileFragment();

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)


        replaceFragment(homePageFragment)
        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(homePageFragment)
                R.id.video -> replaceFragment(exploreFragment)
                R.id.bookmark -> replaceFragment(bookMarkFragment)
                R.id.profile -> replaceFragment(profileFragment)
            }
            true
        }


    }

    private fun replaceFragment(fragment: Fragment) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout, fragment)
            transaction.commit()
        }


    }
}





