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
import com.shivam.devfestblr.activity.TeamDetail
import com.shivam.devfestblr.adapter.TeamAdapter
import com.shivam.devfestblr.model.Team
import kotlinx.android.synthetic.main.fragment_team.*

class TeamFragment : Fragment(), TeamAdapter.TeamListener {
    private var adapter: TeamAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TeamAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        Team.getList().observe(this, Observer {
            adapter?.update(it)
        })
    }

    override fun onTeamSelectListener(imageView: ImageView, item: Team) {
        val activityOptionsCompat =
            ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity!!,
                imageView,
                imageView.transitionName
            )
        val intent = Intent(context, TeamDetail::class.java)
        intent.putExtra("id", item.id)
        startActivity(intent, activityOptionsCompat.toBundle())
    }
}