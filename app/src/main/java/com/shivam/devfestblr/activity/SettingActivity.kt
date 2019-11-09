package com.shivam.devfestblr.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.shivam.devfestblr.R
import com.shivam.devfestblr.fragments.SettingsFragment
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        setActionBar(my_toolbar)
        actionBar?.title = "Settings"
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        my_toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.iconColor))
        my_toolbar.setNavigationOnClickListener { onBackPressed() }
        supportFragmentManager.beginTransaction().replace(R.id.frame, SettingsFragment())
            .commitAllowingStateLoss()
    }
}