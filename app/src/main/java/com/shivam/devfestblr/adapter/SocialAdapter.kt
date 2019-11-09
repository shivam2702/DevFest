package com.shivam.devfestblr.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shivam.devfestblr.R
import com.shivam.devfestblr.model.Social
import kotlinx.android.synthetic.main.item_social.view.*

class SocialAdapter(var listener: SocialListener) :
    RecyclerView.Adapter<SocialAdapter.ViewHolder>() {
    private var mySocial: List<Social> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_social, parent, false
        ), parent.context
    )

    override fun getItemCount(): Int = mySocial.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(mySocial[position])

    fun update(myList: List<Social>) {
        this.mySocial = myList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View, var context: Context) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(item: Social) {
            Glide.with(context).load(item.imageUrl).into(itemView.socialImage)

            itemView.setOnClickListener {
                listener.onSocialSelectListener(itemView.socialImage, item)
            }
        }
    }

    interface SocialListener {
        fun onSocialSelectListener(imageView: ImageView, item: Social)
    }
}