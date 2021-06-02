package com.sf.jetpack.mymov.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.isVisible
import com.sf.jetpack.mymov.BuildConfig
import com.sf.jetpack.mymov.R
import com.sf.jetpack.mymov.adapter.DataCreditAdapter
import com.sf.jetpack.mymov.adapter.DataRecommendationsAdapter
import com.sf.jetpack.mymov.databinding.ActivityDetailTvShowBinding
import com.sf.jetpack.mymov.network.response.TvResultList
import com.sf.jetpack.mymov.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailTvShowActivity : AppCompatActivity() {

    private lateinit var detailBinding: ActivityDetailTvShowBinding
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)
        
        setUpExtra()
        setUpActionBar()
    }

    private fun setUpExtra() {
        val extras = intent.extras
        if (extras != null) {
            val data = extras.getParcelable<TvResultList>(Extra.DATA)
            val selectedId = data?.id
            if (selectedId != null) {
                setUpObserver(selectedId.toString())
                with(detailBinding) {
                    textTvShowName.text = data.name
                    imageMovieCover.loadUrl(BuildConfig.API_URL_IMAGE_ORIGINAL + data.poster_path)
                    imageTvShow.loadUrl(BuildConfig.API_URL_IMAGE_W500 + data.poster_path)
                    val rate = data.vote_average.let { (it * 10) / 20 }
                    ratingBar.rating = rate.toFloat()
                    textRating.text = getString(R.string.app_movie_rating_count, rate)
                }
            }
        }
    }

    private fun setUpObserver(tvShowId: String) {
        viewModel.isLoading.observe(this, { isLoading ->
            with(detailBinding) {
                if (isLoading) {
                    progressLoading.isVisible = true
                    contentTvShowDetail.containerMovieDetail.isVisible = false
                } else {
                    progressLoading.isVisible = false
                    contentTvShowDetail.containerMovieDetail.isVisible = true
                }
            }
        })
        viewModel.getDetailTvFromApi(tvShowId).observe(this, {
            viewModel.isLoading.value = false
            if (it.message == API.MESSAGE_FAIL) {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            } else {
                detailBinding.apply {
                    textTvShowName.text = it.name
                    imageMovieCover.loadUrl(BuildConfig.API_URL_IMAGE_ORIGINAL + it.posterPath)
                    imageTvShow.loadUrl(BuildConfig.API_URL_IMAGE_W500 + it.posterPath)
                    val rate = it.voteAverage?.let { vote -> (vote * 10) / 20 }
                    ratingBar.rating = rate?.toFloat() ?: 0F
                    textRating.text = getString(R.string.app_movie_rating_count, rate)
                    with(this.contentTvShowDetail) {
                        textOverview.text = it.overview
                        val genres = ArrayList<String>()
                        it.genres.forEach { item ->
                            genres.add(item.name)
                        }
                        textGenre.text = genres.joinToString()
                        textReleaseDate.text = convertDate(it.firstAirDate, "yyyy-MM-dd", "dd MMM yyyy")
                    }
                }
            }
        })

        viewModel.getTvShowCredit(tvShowId).observe(this, {
            val dataCreditAdapter = DataCreditAdapter(it.cast)
            with(detailBinding.contentTvShowDetail) {
                val snapHelper = StartSnapHelper()
                snapHelper.attachToRecyclerView(recyclerViewCast)
                recyclerViewCast.addItemDecoration(HorizontalItemDecoration(this@DetailTvShowActivity))
                recyclerViewCast.adapter = dataCreditAdapter
            }
        })

        viewModel.getTvShowRecommendations(tvShowId).observe(this, {
            val movieRecommendationAdapter = DataRecommendationsAdapter(it.results, false)
            with(detailBinding.contentTvShowDetail) {
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}