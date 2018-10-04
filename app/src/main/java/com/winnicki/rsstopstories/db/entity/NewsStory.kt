package com.winnicki.rsstopstories.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

/**
 * Project: rss-top-stories
 * Date: October 03, 2018
 * By: David
 */

@Entity(tableName = "newsStoryData")
data class NewsStory(@PrimaryKey(autoGenerate = false) var id: String,
                     @ColumnInfo(name = "title") var title: String,
                     @ColumnInfo(name = "pubDate") var pubDate: String,
                     @ColumnInfo(name = "author") var author: String,
                     @ColumnInfo(name = "link") var link: String,
                     @ColumnInfo(name = "imageUrl") var imageUrl: String?) {
    @Ignore
    constructor() : this("", "", "", "", "", "")
}
