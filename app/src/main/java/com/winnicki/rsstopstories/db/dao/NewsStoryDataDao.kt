package com.winnicki.rsstopstories.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.winnicki.rsstopstories.db.entity.NewsStory

@Dao
interface NewsStoryDao {

    @Query("SELECT * FROM ${NewsStory.TABLE_NAME}")
    fun getAll(): List<NewsStory>

    @Insert(onConflict = REPLACE)
    fun insert(newsStory: NewsStory)

    @Query("DELETE FROM ${NewsStory.TABLE_NAME}")
    fun deleteAll()
}
