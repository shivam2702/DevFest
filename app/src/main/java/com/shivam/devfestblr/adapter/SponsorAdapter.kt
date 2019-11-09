package com.shivam.devfestblr.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shivam.devfestblr.R
import com.shivam.devfestblr.model.Sponsor
import kotlinx.android.synthetic.main.item_sponsor.view.*

class SponsorAdapter(var listener: SponsorListener) :
    RecyclerView.Adapter<SponsorAdapter.ViewHolder>() {
    private var mySponsor: List<Sponsor> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_sponsor,
                parent,
                false
            ), parent.context
        )

    override fun getItemCount(): Int = mySponsor.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(mySponsor[position])

    fun update(myList: List<Sponsor>) {
        this.mySponsor = myList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View, var context: Context) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(item: Sponsor) {
            itemView.name.text = item.name
            itemView.title.text = item.title
            Glide.with(context).load(item.getImageFullUrl).into(itemView.image)

            itemView.setOnClickListener {
                listener.onSponsorSelectListener(itemView.image, item)
            }
        }
    }

    interface SponsorListener {
        fun onSponsorSelectListener(imageView: ImageView, item: Sponsor)
    }
}