package com.winnicki.rsstopstories.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.winnicki.rsstopstories.R
import com.winnicki.rsstopstories.db.NewsStoriesDatabase
import com.winnicki.rsstopstories.db.entity.NewsStory
import com.winnicki.rsstopstories.ui.web.WebActivity
import com.winnicki.rsstopstories.utils.NetworkHelper
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
        viewModel.init(context)
        initRecyclerView()
    }

    override fun onDestroy() {
        NewsStoriesDatabase.destroyInstace()
        super.onDestroy()
    }

    private fun initRecyclerView() {
        recyclerViewNewsStories.apply {
            adapter = NewsStoryAdapter()
            val layoutManager = LinearLayoutManager(context)
            setLayoutManager(layoutManager)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

            doAsync {
                val newsStories = if (NetworkHelper().isNetworkAvailable(context)) {
                    viewModel.getAllNewsStories()
                } else {
                    viewModel.getAllNewsStoriesOffline()
                }
                uiThread {
                    newsStories?.let { list ->
                        adapter = NewsStoryAdapter(list, object : NewsStoryAdapter.OnItemClickListener {
                            override fun onItemClick(item: NewsStory) {
                                startActivity(WebActivity.getStartIntent(context, item.link))
                            }
                        })
                    }
                }
            }
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
