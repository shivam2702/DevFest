package com.shivam.devfestblr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.shivam.devfestblr.activity.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onResume() {
        super.onResume()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}