package com.winnicki.rsstopstories.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = NewsStory.TABLE_NAME)
data class NewsStory(@PrimaryKey(autoGenerate = false) var id: String,
                     @ColumnInfo(name = "title") var title: String,
                     @ColumnInfo(name = "pub_date") var pubDate: String,
                     @ColumnInfo(name = "author") var author: String,
                     @ColumnInfo(name = "link") var link: String,
                     @ColumnInfo(name = "image_url") var imageUrl: String?) {
    @Ignore
    constructor() : this("", "", "", "", "", "")

    companion object {
        const val TABLE_NAME = "news_story_table"
    }
}
