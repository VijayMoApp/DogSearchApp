package com.ramsoft.dogsearchapp.ui.activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ramsoft.dogsearchapp.R
import dagger.hilt.android.AndroidEntryPoint










/*
Main Activity  Use to navigate other features
 */

@AndroidEntryPoint
class MainActivity  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.custom_toolbar)
        setSupportActionBar(toolbar)


        // Assuming you have a method to load image and name from user data
        val profileImage: ImageView = findViewById(R.id.profileImage)
        val profileName: TextView = findViewById(R.id.profileName)

        // Load image and name from user data
        profileImage.setImageResource(R.drawable.proimage) // Replace with actual image loading logic
        profileName.text = "Pet Lover" // Replace with actual user name


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
        navHostFragment?.let {
            val navController = it.navController

            val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
            NavigationUI.setupWithNavController(bottomNavigationView, navController)
        } ?: run {
            // Handle the error case where the navHostFragment is null
            // e.g., log an error or show a message
        }
    }
}