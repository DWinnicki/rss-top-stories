package com.winnicki.rsstopstories.repository.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.winnicki.rsstopstories.repository.db.entity.NewsStoryData

/**
 * Project: rss-top-stories
 * Date: October 04, 2018
 * By: David
 */

@Dao
interface NewsStoryDataDao {

    @Query("SELECT * from newsStoryData")
    fun getAll(): List<NewsStoryData>

    @Insert(onConflict = REPLACE)
    fun insert(newsStory: NewsStoryData)

    @Query("DELETE from newsStoryData")
    fun deleteAll()
}
