package com.ramsoft.dogsearchapp.ui.activity

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil.load

import com.ramsoft.dogsearchapp.viewmodel.SplashViewModel
import com.ramsoft.dogsearchapp.R

import dagger.hilt.android.AndroidEntryPoint



/*
Splash screen   -  integrate room DB and Random API-  get Image from selected Favorite Image from user .
else get the image from random Dog image api
 */

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val imageView = findViewById<ImageView>(R.id.splashImageView)
        val baseColor = Color.BLACK
        val opacity = 100 // Opacity level (0-255)
        val overlayColor = Color.argb(opacity, Color.red(baseColor), Color.green(baseColor), Color.blue(baseColor))
        val colorFilter = PorterDuffColorFilter(overlayColor, PorterDuff.Mode.SRC_ATOP)
        imageView.setColorFilter(colorFilter)

        lifecycleScope.launchWhenStarted {
            viewModel.imageUrl.collect { url ->
                url?.let {
                    imageView.load(it) {
                        placeholder(R.drawable.placeholder)
                        error(R.drawable.error)
                    }
                }
            }
        }

        // Handler to delay redirection
        Handler(Looper.getMainLooper()).postDelayed({
            navigateToMainActivity()
        }, 3000) // Delay in milliseconds


    }

    fun navigateToMainActivity() {
        if (!isFinishing) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}