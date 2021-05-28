package com.sf.jetpack.mymov.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.sf.jetpack.mymov.BuildConfig.API_URL_IMAGE_ORIGINAL
import com.sf.jetpack.mymov.BuildConfig.API_URL_IMAGE_W500
import com.sf.jetpack.mymov.R
import com.sf.jetpack.mymov.adapter.MovieCreditAdapter
import com.sf.jetpack.mymov.adapter.MovieRecommendationAdapter
import com.sf.jetpack.mymov.databinding.ActivityMovieDetailBinding
import com.sf.jetpack.mymov.network.response.ListData
import com.sf.jetpack.mymov.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

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
        viewModel.isLoading.observe(this, { isLoading ->
            with(detailBinding) {
                if (isLoading) {
                    progressLoading.isVisible = true
                    contentMovieDetail.containerMovieDetail.isVisible = false
                } else {
                    progressLoading.isVisible = false
                    contentMovieDetail.containerMovieDetail.isVisible = true
                }
            }
        })
        viewModel.getDetailMovieFromApi(movieId).observe(this, {
            viewModel.isLoading.value = false
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
            val dataCreditAdapter = MovieCreditAdapter(it.cast)
            with(detailBinding.contentMovieDetail) {
                val snapHelper = StartSnapHelper()
                snapHelper.attachToRecyclerView(recyclerViewCast)
                recyclerViewCast.addItemDecoration(HorizontalItemDecoration(this@DetailMovieActivity))
                recyclerViewCast.adapter = dataCreditAdapter
            }
        })

        viewModel.getMovieRecommendations(movieId).observe(this, {
            val movieRecommendationAdapter = MovieRecommendationAdapter(it.results, true)
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
            val data = extras.getParcelable<ListData>(Extra.DATA)
            val selectedId = data?.id
            if (selectedId != null) {
                setUpObserver(selectedId.toString())
                with(detailBinding) {
                    textMovieName.text = data.title
                    imageMovieCover.loadUrl(API_URL_IMAGE_ORIGINAL + data.poster_path)
                    imageMovie.loadUrl(API_URL_IMAGE_W500 + data.poster_path)
                    val rate = data.vote_average.let { (it * 10) / 20 }
                    ratingBar.rating = rate.toFloat()
                    textRating.text = getString(R.string.app_movie_rating_count, rate)
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}