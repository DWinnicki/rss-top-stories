package com.winnicki.rsstopstories.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.winnicki.rsstopstories.db.entity.NewsStory

/**
 * Project: rss-top-stories
 * Date: October 04, 2018
 * By: David
 */

@Dao
interface NewsStoryDao {

    @Query("SELECT * from newsStoryData")
    fun getAll(): List<NewsStory>

    @Insert(onConflict = REPLACE)
    fun insert(newsStory: NewsStory)
}