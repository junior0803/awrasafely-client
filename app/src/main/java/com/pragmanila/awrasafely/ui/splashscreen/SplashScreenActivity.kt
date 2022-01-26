package com.pragmanila.awrasafely.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import com.pragmanila.awrasafely.R
import com.pragmanila.awrasafely.ui.landingpage.LandingPageActivity
import com.pragmanila.pragmahr.ui.login.LoginActivity

class SplashScreenActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT:Long = 3000 // 1 sec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        Handler().postDelayed({
            val intent = Intent(this, LandingPageActivity::class.java)
//            val anim = ActivityOptionsCompat.makeSceneTransitionAnimation(this
//                , splash_screen_image, ViewCompat.getTransitionName(splash_screen_image).toString()
//            )
            startActivity(intent)
        }, SPLASH_TIME_OUT)

    }
}