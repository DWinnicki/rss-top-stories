package com.winnicki.rsstopstories.ui.web

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import com.winnicki.rsstopstories.R
import com.winnicki.rsstopstories.ext.snack
import com.winnicki.rsstopstories.utils.NetworkHelper
import kotlinx.android.synthetic.main.web_fragment.*

class WebFragment : Fragment() {

    private lateinit var viewModel: WebViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.web_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WebViewModel::class.java)
        loadWebPage()
    }

    private fun loadWebPage() {
        val link = arguments?.getString(NEWS_STORY_EXTRA)
        webView.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        webView.loadUrl(link)
        if (!NetworkHelper().isNetworkAvailable(context)) {
            view?.snack("No Internet Connection")
        }
    }

    companion object {
        private const val NEWS_STORY_EXTRA = "NEWS_STORY_EXTRA"

        fun newInstance() = WebFragment()
    }
}
