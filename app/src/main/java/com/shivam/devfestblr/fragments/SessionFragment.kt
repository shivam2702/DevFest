package com.shivam.devfestblr.fragments


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.shivam.devfestblr.R
import com.shivam.devfestblr.activity.SessionDetail
import com.shivam.devfestblr.adapter.SessionAdapter
import com.shivam.devfestblr.model.Favorites
import com.shivam.devfestblr.model.Session
import kotlinx.android.synthetic.main.fragment_session.*

class SessionFragment : Fragment(), SessionAdapter.SessionListener, View.OnClickListener {

    private var adapter: SessionAdapter? = null
    private var lastSelectedChip: Chip? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_session, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = SessionAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        Favorites.updateListener.observe(this, Observer {
            adapter?.notifyDataSetChanged()
        })

        val list = Session.getDistinctLocation()
        list.forEach {
            createChip(it)
        }

        if (chip_group.size > 0) {
            chip_group[0].performClick()
        }
    }

    private fun filterSearch(filter: String) {
        Session.getByLocation(filter).observe(this, Observer {
            adapter?.update(it)
        })
    }

    private fun createChip(title: String) {
        val chip = Chip(context)
        chip.text = title
        chip.isClickable = true
        chip.chipStrokeWidth = 8f
        chip.setChipStrokeColorResource(R.color.colorPrimary)
        chip.unSelect()
        chip_group.addView(chip)
        chip.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val chip = view as Chip
        chip.select()
        if (chip.text != lastSelectedChip?.text) {
            lastSelectedChip?.unSelect()
            lastSelectedChip = chip
            filterSearch(lastSelectedChip?.text.toString())
        }
    }

    private fun Chip.select() {
        setChipBackgroundColorResource(R.color.colorPrimary)
        setTextColor(ContextCompat.getColor(context, android.R.color.white))
    }

    private fun Chip.unSelect() {
        setChipBackgroundColorResource(android.R.color.transparent)
        setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
    }

    override fun onSessionSelectListener(item: Session) {
        val intent = Intent(context, SessionDetail::class.java)
        intent.putExtra("id", item.id)
        startActivity(intent)
        activity?.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}