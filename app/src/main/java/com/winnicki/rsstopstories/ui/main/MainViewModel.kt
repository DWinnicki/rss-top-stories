package com.winnicki.rsstopstories.ui.main

import android.arch.lifecycle.ViewModel
import com.winnicki.rsstopstories.db.NewsStoriesDatabase
import com.winnicki.rsstopstories.utils.RssFeedProvider

class MainViewModel : ViewModel() {

    fun getAllNewsStories(db: NewsStoriesDatabase) =
            RssFeedProvider().parseUrl(RSS_FEED_URL, db)

    fun getAllNewsStoriesOffline(db: NewsStoriesDatabase) =
            db.newsStoryDao().getAll()

    companion object {
        const val RSS_FEED_URL = "https://www.cbc.ca/cmlink/rss-topstories"
    }
}
