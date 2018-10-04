package com.winnicki.rsstopstories.ui.web

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.winnicki.rsstopstories.R

class WebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_activity)
        supportActionBar?.hide()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, WebFragment.newInstance().apply {
                        arguments = intent.extras
                    })
                    .commitNow()
        }
    }

    companion object {
        private const val NEWS_STORY_EXTRA = "NEWS_STORY_EXTRA"

        fun getStartIntent(context: Context, link: String): Intent {
            val intent = Intent(context, WebActivity::class.java)
            intent.putExtra(NEWS_STORY_EXTRA, link)
            return intent
        }
    }
}
