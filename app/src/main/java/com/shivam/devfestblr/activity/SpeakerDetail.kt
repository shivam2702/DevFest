package com.shivam.devfestblr.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shivam.devfestblr.R
import com.shivam.devfestblr.adapter.SessionAdapter
import com.shivam.devfestblr.adapter.SocialAdapter
import com.shivam.devfestblr.helpers.openEmail
import com.shivam.devfestblr.helpers.openLink
import com.shivam.devfestblr.model.*
import kotlinx.android.synthetic.main.activity_speaker_detail.*

class SpeakerDetail : AppCompatActivity(), SessionAdapter.SessionListener,
    SocialAdapter.SocialListener {
    private var id: String? = null
    private var adapter: SessionAdapter? = null
    private var adapterSocial: SocialAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speaker_detail)
        setActionBar(my_toolbar)
        actionBar?.title = ""
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        my_toolbar.setNavigationOnClickListener { onBackPressed() }

        id = intent.extras?.getString("id")

        recyclerview.layoutManager = LinearLayoutManager(this)
        adapter = SessionAdapter(this)
        recyclerview.adapter = adapter

        socialRecycler.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        adapterSocial = SocialAdapter(this)
        socialRecycler.adapter = adapterSocial


        Speaker.getInfo(id!!).observe(this, Observer {
            setSpeaker(it)
        })

        Favorites.updateListener.observe(this, Observer {
            adapter?.notifyDataSetChanged()
        })
    }

    override fun onSessionSelectListener(item: Session) {
        val intent = Intent(this, SessionDetail::class.java)
        intent.putExtra("id", item.id)
        startActivity(intent)
    }

    private fun setSpeaker(item: Speaker) {
        name.text = item.name

        my_title.text = item.title
        company.text = item.company
        Glide.with(this).load(item.imageUrl).into(image)

        long_description.text = item.bio

        val list = Session.getSessionBySpeaker(item.sessionId)

        if (list.isEmpty()) {
            session_header.visibility = View.GONE
            recyclerview.visibility = View.GONE
        } else {
            session_header.visibility = View.VISIBLE
            recyclerview.visibility = View.VISIBLE
            adapter?.update(ArrayList(list))
        }

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