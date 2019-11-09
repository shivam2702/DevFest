package com.shivam.devfestblr.activity

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.shivam.devfestblr.BaseActivity
import com.shivam.devfestblr.MainViewModel
import com.shivam.devfestblr.R
import com.shivam.devfestblr.fragments.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun getPageName(): String = "Main Activity"

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun getLayout(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bottom_nav.setOnNavigationItemSelectedListener {
            viewModel.fragIndex = it.itemId
            val currentFragment = supportFragmentManager.findFragmentById(R.id.frame)
            val frag: Fragment? =
                when (viewModel.fragIndex) {
                    R.id.action_schedule -> {
                        if (currentFragment !is SessionFragment) SessionFragment() else null
                    }
                    R.id.action_speaker -> {
                        if (currentFragment !is SpeakerFragment) SpeakerFragment() else null
                    }
                    R.id.action_team -> {
                        if (currentFragment !is TeamFragment) TeamFragment() else null
                    }
                    R.id.action_sponsor -> {
                        if (currentFragment !is SponsorFragment) SponsorFragment() else null
                    }
                    else -> {
                        if (currentFragment !is HomeFragment) HomeFragment() else null
                    }
                }
            if (frag != null) supportFragmentManager.beginTransaction().replace(
                R.id.frame,
                frag,
                "homefrag"
            ).commit()
            true
        }

        setting.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }
        bottom_nav.selectedItemId = viewModel.fragIndex
    }

    override fun onBackPressed() {
        if (viewModel.fragIndex != R.id.action_home) {
            bottom_nav.selectedItemId = R.id.action_home
        } else
            super.onBackPressed()
    }
}