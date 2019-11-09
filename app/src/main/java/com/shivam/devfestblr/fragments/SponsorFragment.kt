package com.shivam.devfestblr.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.shivam.devfestblr.R
import com.shivam.devfestblr.adapter.SponsorAdapter
import com.shivam.devfestblr.model.Sponsor
import kotlinx.android.synthetic.main.fragment_sponsor.*

class SponsorFragment : Fragment(), SponsorAdapter.SponsorListener {

    private var adapter: SponsorAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sponsor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = SponsorAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        Sponsor.getList().observe(this, Observer {
            adapter?.update(it)
        })
    }

    override fun onSponsorSelectListener(imageView: ImageView, item: Sponsor) {
//        val activityOptionsCompat =
//            ActivityOptionsCompat.makeSceneTransitionAnimation(activity!!, imageView, imageView.transitionName)
//        val intent = Intent(context, SponsorDetail::class.java)
//        intent.putExtra("id", item.id)
//        startActivity(intent, activityOptionsCompat.toBundle())
    }
}