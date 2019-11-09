package com.shivam.devfestblr.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shivam.devfestblr.R
import com.shivam.devfestblr.adapter.SocialAdapter
import com.shivam.devfestblr.helpers.openEmail
import com.shivam.devfestblr.helpers.openLink
import com.shivam.devfestblr.model.Social
import com.shivam.devfestblr.model.SocialType
import com.shivam.devfestblr.model.Team
import kotlinx.android.synthetic.main.activity_team_detail.*

class TeamDetail : AppCompatActivity(), SocialAdapter.SocialListener {
    private var id: String? = null
    private var adapterSocial: SocialAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)
        setActionBar(my_toolbar)
        actionBar?.title = ""
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        my_toolbar.setNavigationOnClickListener { onBackPressed() }

        socialRecycler.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        adapterSocial = SocialAdapter(this)
        socialRecycler.adapter = adapterSocial

        id = intent.extras?.getString("id")

        Team.getInfo(id!!).observe(this, Observer {
            setTeam(it)
        })
    }

    private fun setTeam(item: Team) {
        name.text = item.name

        my_title.text = item.title
        company.text = item.company
        Glide.with(this).load(item.getImageFullUrl).into(image)

        long_description.text = item.bio

        if (item.socialList.isEmpty()) {
            socialRecycler.visibility = View.GONE
        } else {
            socialRecycler.visibility = View.VISIBLE
            adapterSocial?.update(item.socialList)
        }
    }

    override fun onSocialSelectListener(imageView: ImageView, item: Social) {
        when (item.type) {
            SocialType.WEB -> {
                openLink(this, item.url)
            }
            SocialType.EMAIL -> {
                openEmail(item.url)
            }
        }
    }
}