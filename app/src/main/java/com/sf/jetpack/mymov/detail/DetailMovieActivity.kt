package com.sf.jetpack.mymov.detail

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.appbar.AppBarLayout
import com.sf.jetpack.mymov.BuildConfig.API_URL_IMAGE_ORIGINAL
import com.sf.jetpack.mymov.BuildConfig.API_URL_IMAGE_W500
import com.sf.jetpack.mymov.R
import com.sf.jetpack.mymov.adapter.MovieCreditAdapter
import com.sf.jetpack.mymov.adapter.MovieRecommendationAdapter
import com.sf.jetpack.mymov.databinding.ActivityMovieDetailBinding
import com.sf.jetpack.mymov.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.collections.ArrayList

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var detailBinding: ActivityMovieDetailBinding
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        setUpActionBar()
        setUpExtra()
    }

    private fun setUpObserver(movieId: String) {
        viewModel.getDetailMovieFromApi(movieId, this).observe(this, {
            detailBinding.apply {
                textMovieName.text = it.originalTitle
                imageMovieCover.loadUrl(API_URL_IMAGE_ORIGINAL + it.posterPath)
                imageMovie.loadUrl(API_URL_IMAGE_W500 + it.posterPath)
                val rate = it.voteAverage?.let { (it * 10) / 20 }
                ratingBar.rating = rate?.toFloat() ?: 0F
                textRating.text = getString(R.string.app_movie_rating_count, rate)
                with(this.contentMovieDetail) {
                    textReleaseDate.text = it.releaseDate
                    textOverview.text = it.overview
                    val genres = ArrayList<String>()
                    it.genres.forEach { item ->
                        genres.add(item.name)
                    }
                    textGenre.text = genres.joinToString()
                    textReleaseDate.text = convertDate(it.releaseDate, "yyyy-MM-dd", "dd MMM yyyy")
                }
            }
        })

        viewModel.getMovieCredit(movieId).observe(this, {
            val movieCreditAdapter = MovieCreditAdapter(it.cast)
            with(detailBinding.contentMovieDetail) {
                val snapHelper = StartSnapHelper()
                snapHelper.attachToRecyclerView(recyclerViewCast)
                recyclerViewCast.addItemDecoration(HorizontalItemDecoration(this@DetailMovieActivity))
                recyclerViewCast.adapter = movieCreditAdapter
            }
        })

        viewModel.getMovieRecommendations(movieId).observe(this, {
            val movieRecommendationAdapter = MovieRecommendationAdapter(it.results)
            with(detailBinding.contentMovieDetail) {
                recyclerViewMovieRecommendations.addItemDecoration(
                    GridItemDecoration(
                        resources.getDimensionPixelSize(
                            R.dimen.marginM
                        )
                    )
                )
                recyclerViewMovieRecommendations.adapter = movieRecommendationAdapter
            }
        })
    }

    private fun setUpActionBar() {
        setSupportActionBar(detailBinding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    private fun setUpExtra() {
        val extras = intent.extras
        if (extras != null) {
            val selectedId = extras.getString(Extra.ID)
            if (selectedId != null) {
                viewModel.setSelectedId(selectedId)
                setUpObserver(selectedId)
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