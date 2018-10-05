package com.winnicki.rsstopstories.ui.main

import android.arch.lifecycle.ViewModel
import android.content.Context
import com.winnicki.rsstopstories.db.NewsStoriesDatabase
import com.winnicki.rsstopstories.db.dao.NewsStoryDao
import com.winnicki.rsstopstories.db.entity.NewsStory
import com.winnicki.rsstopstories.utils.RssFeedProvider

class MainViewModel : ViewModel() {

    private var newsStoryDao: NewsStoryDao? = null

    fun init(context: Context?) {
        context?.let {
            val database = NewsStoriesDatabase.getInstance(it)
            newsStoryDao = database?.newsStoryDao()
        }
    }

    fun getAllNewsStories(): List<NewsStory> {
        newsStoryDao?.deleteAll()
        return RssFeedProvider().parseUrl(RSS_FEED_URL, newsStoryDao)
    }

    fun getAllNewsStoriesOffline() = newsStoryDao?.getAll()

    companion object {
        const val RSS_FEED_URL = "https://www.cbc.ca/cmlink/rss-topstories"
    }
}
