package com.winnicki.rsstopstories.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.winnicki.rsstopstories.db.dao.NewsStoryDao
import com.winnicki.rsstopstories.db.entity.NewsStory

@Database(entities = [NewsStory::class], version = 1, exportSchema = false)
abstract class NewsStoriesDatabase : RoomDatabase() {

    abstract fun newsStoryDao(): NewsStoryDao

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
