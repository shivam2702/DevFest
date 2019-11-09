package com.shivam.devfestblr

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.shivam.devfestblr.fragments.*

class MainViewModel(app: Application) : AndroidViewModel(app) {
    var fragIndex: Int = R.id.action_home

    var sessionFrag = SessionFragment()
    var speakerFrag = SpeakerFragment()
    var homeFrag = HomeFragment()
    var sponsorFrag = SponsorFragment()
    var teamFrag = TeamFragment()
}