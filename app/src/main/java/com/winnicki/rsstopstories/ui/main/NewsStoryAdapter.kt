package com.winnicki.rsstopstories.ui.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.winnicki.rsstopstories.R
import com.winnicki.rsstopstories.db.entity.NewsStory
import kotlinx.android.synthetic.main.news_story_list_row.view.*

/**
 * Project: rss-top-stories
 * Date: October 03, 2018
 * By: David
 */

class NewsStoryAdapter(private val newsStoryList: List<NewsStory>,
                       private val listener: OnItemClickListener) : RecyclerView.Adapter<NewsStoryAdapter.NewsStoryViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(item: NewsStory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsStoryAdapter.NewsStoryViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.news_story_list_row, parent, false)
        return NewsStoryViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return newsStoryList.size
    }

    override fun onBindViewHolder(holder: NewsStoryAdapter.NewsStoryViewHolder, position: Int) {
        holder.bind(newsStoryList[position], listener)
    }

    class NewsStoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title: TextView = view.textViewTitle
        private val pubDate: TextView = view.textViewPubDate
        private val author: TextView = view.textViewAuthor
        private val image: ImageView = view.imageViewArtwork

        fun bind(item: NewsStory, listener: OnItemClickListener) {
            title.text = item.title
            pubDate.text = item.pubDate
            author.text = item.author

            Glide.with(itemView)
                    .load(item.imageUrl)
                    .apply(RequestOptions().placeholder(R.drawable.placeholder))
                    .into(image)

            itemView.setOnClickListener {
                listener.onItemClick(item)
            }
        }
    }
}
