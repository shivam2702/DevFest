package com.shivam.devfestblr.fragments


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.shivam.devfestblr.R
import com.shivam.devfestblr.activity.SpeakerDetail
import com.shivam.devfestblr.adapter.SpeakerAdapter
import com.shivam.devfestblr.model.Favorites
import com.shivam.devfestblr.model.Speaker
import kotlinx.android.synthetic.main.fragment_session.*


class SpeakerFragment : Fragment(), SpeakerAdapter.SpeakerListener {
    private var adapter: SpeakerAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_speaker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = SpeakerAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        Speaker.getAll().observe(this, Observer {
            adapter?.update(it)
        })

        Favorites.updateListener.observe(this, Observer {
            adapter?.notifyDataSetChanged()
        })
    }

    override fun onSpeakerSelectListener(imageView: ImageView, item: Speaker) {
        val activityOptionsCompat =
            ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity!!,
                imageView,
                imageView.transitionName
            )
        val intent = Intent(context, SpeakerDetail::class.java)
        intent.putExtra("id", item.id)
        startActivity(intent, activityOptionsCompat.toBundle())
    }
}