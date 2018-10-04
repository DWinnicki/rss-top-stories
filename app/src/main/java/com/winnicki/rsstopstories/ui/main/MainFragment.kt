package com.winnicki.rsstopstories.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.winnicki.rsstopstories.R
import com.winnicki.rsstopstories.repository.model.entity.NewsStory
import com.winnicki.rsstopstories.ui.web.WebActivity
import com.winnicki.rsstopstories.utils.RssFeedProvider
import kotlinx.android.synthetic.main.main_fragment.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerViewNewsStories.apply {
            val layoutManager = LinearLayoutManager(context)
            setLayoutManager(layoutManager)
            itemAnimator = DefaultItemAnimator()

            doAsync {
                val data = RssFeedProvider().parseUrl(RSS_FEED_URL)
                uiThread {
                    adapter = NewsStoryAdapter(data, object : NewsStoryAdapter.OnItemClickListener {
                        override fun onItemClick(item: NewsStory) {
                            startActivity(WebActivity.getStartIntent(context, item.link))
                        }
                    })
                }
            }
        }
    }

    companion object {
        const val RSS_FEED_URL = "https://www.cbc.ca/cmlink/rss-topstories"

        fun newInstance() = MainFragment()
    }
}
