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
import com.shivam.devfestblr.model.Favorites
import com.shivam.devfestblr.model.Speaker
import kotlinx.android.synthetic.main.item_speaker.view.*

class SpeakerAdapter(var listener: SpeakerListener) :
    RecyclerView.Adapter<SpeakerAdapter.ViewHolder>() {
    private var mySpeakers: List<Speaker> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_speaker, parent, false
        ), parent.context
    )

    override fun getItemCount(): Int = mySpeakers.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(mySpeakers[position])

    fun update(myList: List<Speaker>) {
        this.mySpeakers = myList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View, var context: Context) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(item: Speaker) {
            val isLiked = Favorites.isLiked(item.id).isNotEmpty()
            itemView.favourite.setImageResource(if (isLiked) R.drawable.ic_star_selected else R.drawable.ic_star_unselect)

            itemView.name.text = item.name
            itemView.company.text = item.company

            itemView.image.setTextAndColorSeed(item.name.getInitials(), item.name)
            Glide
                .with(context)
                .load(item.imageUrl)
                .into(itemView.image)

            itemView.setOnClickListener {
                listener.onSpeakerSelectListener(itemView.image, item)
            }

            itemView.favourite.setOnClickListener { item.toggleFav(isLiked) }
        }
    }

    interface SpeakerListener {
        fun onSpeakerSelectListener(imageView: ImageView, item: Speaker)
    }
}