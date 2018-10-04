package com.winnicki.rsstopstories.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.winnicki.rsstopstories.db.dao.NewsStoryDataDao
import com.winnicki.rsstopstories.db.entity.NewsStory

/**
 * Project: rss-top-stories
 * Date: October 03, 2018
 * By: David
 */

@Database(entities = [NewsStory::class], version = 1, exportSchema = false)
abstract class NewsStoriesDatabase : RoomDatabase() {

    abstract fun newsStoryDataDao(): NewsStoryDataDao

    companion object {
        private var INSTANCE: NewsStoriesDatabase? = null

        fun getInstance(context: Context): NewsStoriesDatabase? {
            if (INSTANCE == null) {
                synchronized(NewsStoriesDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            NewsStoriesDatabase::class.java, "rssTopStories.db")
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstace() {
            INSTANCE = null
        }
    }
}
