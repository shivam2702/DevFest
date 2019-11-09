package com.shivam.devfestblr.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shivam.devfestblr.R
import com.shivam.devfestblr.helpers.getInitials
import com.shivam.devfestblr.model.Team
import kotlinx.android.synthetic.main.item_team.view.*

class TeamAdapter(var listener: TeamListener) : RecyclerView.Adapter<TeamAdapter.ViewHolder>() {
    private var myTeam: List<Team> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_team, parent, false
        ), parent.context
    )

    override fun getItemCount(): Int = myTeam.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(myTeam[position])

    fun update(myList: List<Team>) {
        this.myTeam = ArrayList()
        this.myTeam = myList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View, var context: Context) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(item: Team) {
            itemView.name.text = item.name
            itemView.title.text = item.title
            itemView.company.text = item.company
            itemView.image.setTextAndColorSeed(item.name.getInitials(), item.name)
            Glide.with(context).load(item.getImageFullUrl).into(itemView.image)

            itemView.setOnClickListener {
                listener.onTeamSelectListener(itemView.image, item)
            }
        }
    }

    interface TeamListener {
        fun onTeamSelectListener(imageView: ImageView, item: Team)
    }
}