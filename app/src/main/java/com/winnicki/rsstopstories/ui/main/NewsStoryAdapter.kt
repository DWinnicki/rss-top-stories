package com.winnicki.rsstopstories.ui.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.winnicki.rsstopstories.R
import com.winnicki.rsstopstories.repository.model.entity.NewsStory
import kotlinx.android.synthetic.main.news_story_list_row.view.*

/**
 * Project: rss-top-stories
 * Date: October 03, 2018
 * By: David
 */

class NewsStoryAdapter(private val newsStoryList: List<NewsStory>) : RecyclerView.Adapter<NewsStoryAdapter.NewsStoryViewHolder>() {

    class NewsStoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView? = null
        var pubDate: TextView? = null
        var author: TextView? = null

        init {
            title = view.textViewTitle
            pubDate = view.textViewPubDate
            author = view.textViewAuthor
        }
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
        val newsStory = newsStoryList[position]
        holder.title?.text = newsStory.title
        holder.pubDate?.text = newsStory.pubDate
        holder.author?.text = newsStory.author
    }
}
