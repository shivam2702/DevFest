package com.shivam.devfestblr.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shivam.devfestblr.R
import com.shivam.devfestblr.model.Favorites
import com.shivam.devfestblr.model.Session
import kotlinx.android.synthetic.main.item_session.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class SessionAdapter(var listener: SessionListener) :
    RecyclerView.Adapter<SessionAdapter.ViewHolder>() {
    private var mySessions: List<Session> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_session, parent, false
        ), parent.context
    )

    override fun getItemCount(): Int = mySessions.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(mySessions[position])

    fun update(myList: List<Session>) {
        this.mySessions = myList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View, var context: Context) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(item: Session) {
            val isLiked = Favorites.isLiked(item.id).isNotEmpty()
            itemView.favourite.setImageResource(if (isLiked) R.drawable.ic_star_selected else R.drawable.ic_star_unselect)
            itemView.startTime.text = getTime(item.startTime)
            itemView.meridian.text = getMeridian(item.startTime)
            itemView.name.text = item.name
            itemView.company.text = item.location

            itemView.setOnClickListener {
                listener.onSessionSelectListener(item)
            }

            itemView.favourite.setOnClickListener { item.toggleFav(isLiked) }
        }
    }

    interface SessionListener {
        fun onSessionSelectListener(item: Session)
    }

    private fun getTime(startTime: Long): String {
        val startDateFormatter = SimpleDateFormat("hh:mm", Locale.getDefault())
        return startDateFormatter.format(Date(startTime))
    }

    private fun getMeridian(startTime: Long): String {
        val startDateFormatter = SimpleDateFormat("a", Locale.getDefault())
        return startDateFormatter.format(Date(startTime))
    }
}