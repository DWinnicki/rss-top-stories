package com.winnicki.rsstopstories.repository.model.entity

/**
 * Project: rss-top-stories
 * Date: October 03, 2018
 * By: David
 */

data class NewsStory(val title: String,
                     val pubDate: String,
                     val author: String,
                     val imageUrl: String)