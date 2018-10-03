package com.winnicki.rsstopstories.ui.main

import android.arch.lifecycle.ViewModel
import com.winnicki.rsstopstories.repository.model.entity.NewsStory

class MainViewModel : ViewModel() {

    fun getNewsStories() = emptyList<NewsStory>()
}
