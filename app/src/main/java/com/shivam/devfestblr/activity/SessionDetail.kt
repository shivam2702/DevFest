package com.shivam.devfestblr.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.shivam.devfestblr.MyApplication
import com.shivam.devfestblr.R
import com.shivam.devfestblr.adapter.SpeakerAdapter
import com.shivam.devfestblr.model.Favorites
import com.shivam.devfestblr.model.Session
import com.shivam.devfestblr.model.Speaker
import kotlinx.android.synthetic.main.activity_session_detail.*
import java.text.SimpleDateFormat
import java.util.*

class SessionDetail : AppCompatActivity(), SpeakerAdapter.SpeakerListener {
    private var id: String? = null
    private var adapter: SpeakerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_session_detail)
        setActionBar(my_toolbar)
        actionBar?.title = ""
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        my_toolbar.setNavigationOnClickListener { onBackPressed() }

        id = intent.extras?.getString("id")

        recyclerview.layoutManager = LinearLayoutManager(this)
        adapter = SpeakerAdapter(this)
        recyclerview.adapter = adapter
        MyApplication.database.sessionDao().getInfo(id!!).observe(this, Observer {
            setSession(it)
        })

        Favorites.updateListener.observe(this, Observer {
            adapter?.notifyDataSetChanged()
        })
    }

    private fun setSession(session: Session) {
        name.text = session.name

        my_title.text = getDate(session.startTime, session.endTime)
        company.text = session.location

        long_description.text = session.longDescription

        if (session.speakerId.isEmpty()) {
            session_header.visibility = View.GONE
            recyclerview.visibility = View.GONE
        } else {
            session_header.visibility = View.VISIBLE
            recyclerview.visibility = View.VISIBLE
            val speakers = Speaker.getList(session.speakerId)
            adapter?.update(speakers)
        }
    }

    override fun onPause() {
        super.onPause()
        if (isFinishing) overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    override fun onSpeakerSelectListener(imageView: ImageView, item: Speaker) {
        val activityOptionsCompat =
            ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                imageView,
                imageView.transitionName
            )
        val intent = Intent(this, SpeakerDetail::class.java)
        intent.putExtra("id", item.id)
        startActivity(intent, activityOptionsCompat.toBundle())

    }

    private fun getDate(startTime: Long, endTime: Long): String {
        val startDateFormatter = SimpleDateFormat("EEE, MMM d, hh:mm", Locale.getDefault())
        val endDateFormatter = SimpleDateFormat("hh:mm a", Locale.getDefault())
        return "${startDateFormatter.format(Date(startTime))} - ${endDateFormatter.format(
            Date(
                endTime
            )
        )} "
    }
}