package com.sf.jetpack.mymov.detail

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.sf.jetpack.mymov.data.Movie
import com.sf.jetpack.mymov.data.TvShowsData
import com.sf.jetpack.mymov.databinding.ActivityDetailBinding
import com.sf.jetpack.mymov.utils.CustomWebClient
import com.sf.jetpack.mymov.utils.Extra
import com.sf.jetpack.mymov.utils.TYPE
import com.sf.jetpack.mymov.utils.loadVideo

class DetailActivity : AppCompatActivity() {

    private lateinit var detailBinding: ActivityDetailBinding
    private val viewModel: DetailMovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        setUpActionBar()
        setUpExtra()
    }

    private fun setUpActionBar() {
        setSupportActionBar(detailBinding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setUpExtra() {
        val extras = intent.extras
        if (extras != null) {
            val selectedId = extras.getString(Extra.ID)
            if (selectedId != null) {
                viewModel.setSelectedId(selectedId)
                var data: Any? = null
                if (intent.extras != null) {
                    val type = extras.getString(Extra.TYPE)
                    data = if (type == TYPE.MOVIE.name) {
                        viewModel.getDetailMovie()
                    } else {
                        viewModel.getDetailTvShow()
                    }
                }
                setupView(data)
            }
        }
    }

    private fun setupView(data: Any?) {
        detailBinding.apply {
            if (data is Movie) {
                textMovieName.text = data.name
                textPublishedYear.text = data.publishedYear
                textMovieDescription.text = data.description
                textDirectorName.text = data.director
                if (data.linkTrailer != null) {
                    webView.isVisible = true
                    textLinkNotFound.isVisible = false
                    val videoUrl = data.linkTrailer.loadVideo()
                    webView.settings.apply {
                        loadWithOverviewMode = true
                        defaultFontSize = 18
                        javaScriptEnabled = true
                        loadWithOverviewMode = true
                    }
                    webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
                    webView.webChromeClient = CustomWebClient(this@DetailActivity)
                    webView.loadData(videoUrl, "text/html", "utf-8")
                } else {
                    webView.isVisible = false
                    textLinkNotFound.isVisible = true
                }
                val movieImage = resources.getIdentifier(data.image, null, packageName)
                movieImage.let {
                    imageMovies.setImageDrawable(
                        ContextCompat.getDrawable(
                            this@DetailActivity,
                            it
                        )
                    )
                }
            } else if (data is TvShowsData) {
                textMovieName.text = data.name
                textPublishedYear.text = data.publishedYear
                textMovieDescription.text = data.description
                textDirectorName.text = data.creator
                if (data.linkTrailer != null) {
                    webView.isVisible = true
                    textLinkNotFound.isVisible = false
                    val videoUrl = data.linkTrailer.loadVideo()
                    webView.settings.apply {
                        loadWithOverviewMode = true
                        defaultFontSize = 18
                        javaScriptEnabled = true
                        loadWithOverviewMode = true
                    }
                    webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
                    webView.webChromeClient = CustomWebClient(this@DetailActivity)
                    webView.loadData(videoUrl, "text/html", "utf-8")
                } else {
                    webView.isVisible = false
                    textLinkNotFound.isVisible = true
                }
                val movieImage = resources.getIdentifier(data.image, null, packageName)
                movieImage.let {
                    imageMovies.setImageDrawable(
                        ContextCompat.getDrawable(
                            this@DetailActivity,
                            it
                        )
                    )
                }
            }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            window.decorView.systemUiVisibility = View.VISIBLE
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}