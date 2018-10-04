package com.winnicki.rsstopstories.repository.model.entity

/**
 * Project: rss-top-stories
 * Date: October 03, 2018
 * By: David
 */

data class NewsStory(var title: String,
                     var pubDate: String,
                     var author: String,
                     var link: String,
                     var imageUrl: String?) {
    constructor() : this("", "", "", "", "")
}