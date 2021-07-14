package com.sf.jetpack.mymov.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.sf.jetpack.mymov.databinding.ActivitySplashScreenBinding
import com.sf.jetpack.mymov.home.HomeActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var splashBinding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)

        setUpSplashScreen()
    }

    private fun setUpSplashScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
//            Intent(this, HomeActivity::class.java).apply {
//                startActivity(this)
//                finish()
//            }
            Intent(this, HomeActivity::class.java).apply {
                startActivity(this)
                finish()
            }
        }, 1000)
    }
}