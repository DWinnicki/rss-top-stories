package com.winnicki.rsstopstories.utils

import android.util.Xml
import com.winnicki.rsstopstories.repository.model.entity.NewsStory
import org.xmlpull.v1.XmlPullParser
import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL

/**
 * Project: rss-top-stories
 * Date: October 03, 2018
 * By: David
 */

class RssFeedProvider {

    fun parseUrl(url: String): List<NewsStory> {
        val list = ArrayList<NewsStory>()
        val parser = Xml.newPullParser()
        var stream: InputStream? = null
        try {
            stream = URL(url).openConnection().getInputStream()
            parser.setInput(stream, null)
            var eventType = parser.eventType
            var item: NewsStory? = null
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val element = parser.name
                when (eventType) {
                    XmlPullParser.START_TAG -> {
                        if (element.equals(ITEM, ignoreCase = true)) {
                            item = NewsStory()
                        } else if (item != null) {
                            when {
                                element.equals(TITLE, ignoreCase = true) -> item.title = parser.nextText().trim()
                                element.equals(PUB_DATE, ignoreCase = true) -> item.pubDate = parser.nextText().trim()
                                element.equals(AUTHOR, ignoreCase = true) -> item.author = parser.nextText().trim()
                                element.equals(LINK, ignoreCase = true) -> item.link = parser.nextText().trim()
                                element.equals(DESCRIPTION, ignoreCase = true) -> item.imageUrl = parseImageUrl(parser.nextText().trim())
                            }
                        }
                    }
                    XmlPullParser.END_TAG -> {
                        if (element.equals(ITEM, ignoreCase = true) && item != null) {
                            list.add(item)
                        }
                    }
                }
                eventType = parser.next()
            }
        } catch (e: Exception) {
            throw RuntimeException(e)
        } finally {
            if (stream != null) {
                try {
                    stream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }
        return list
    }

    private fun parseImageUrl(xmlString: String): String? {
        val parser = Xml.newPullParser()
        var stream: ByteArrayInputStream? = null
        try {
            stream = xmlString.byteInputStream()
            parser.setInput(stream, null)
            var eventType = parser.eventType

            while (eventType != XmlPullParser.END_DOCUMENT) {
                when (eventType) {
                    XmlPullParser.START_TAG -> {
                        val name = parser.name
                        if (IMG == name) {
                            return parser.getAttributeValue(null, SRC).trim()
                        }
                    }
                }
                eventType = parser.next()
            }
        } catch (e: Exception) {
            throw RuntimeException(e)
        } finally {
            if (stream != null) {
                try {
                    stream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }
        return null
    }

    companion object {
        const val AUTHOR = "author"
        const val DESCRIPTION = "description"
        const val IMG = "img"
        const val ITEM = "item"
        const val LINK = "link"
        const val PUB_DATE = "pubDate"
        const val SRC = "src"
        const val TITLE = "title"
    }
}