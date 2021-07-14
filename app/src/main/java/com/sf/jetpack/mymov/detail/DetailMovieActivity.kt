package com.sf.jetpack.mymov.detail

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.sf.jetpack.mymov.BuildConfig.API_URL_IMAGE_ORIGINAL
import com.sf.jetpack.mymov.BuildConfig.API_URL_IMAGE_W500
import com.sf.jetpack.mymov.R
import com.sf.jetpack.mymov.adapter.DataCreditAdapter
import com.sf.jetpack.mymov.adapter.DataRecommendationsAdapter
import com.sf.jetpack.mymov.databinding.ActivityMovieDetailBinding
import com.sf.jetpack.mymov.db.FavoriteEntity
import com.sf.jetpack.mymov.network.response.ListData
import com.sf.jetpack.mymov.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.ClassCastException
import java.util.*

/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By Fahmi
 */

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var detailBinding: ActivityMovieDetailBinding
    private val viewModel: DetailViewModel by viewModel()
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityMovieDetailBinding.inflate(layoutInflater)
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

    private fun setUpExtra() {
        val extras = intent.extras
        if (extras != null) {
            val data = try {
                extras.getParcelable<ListData>(Extra.DATA)
            } catch (e: ClassCastException) {
                extras.getParcelable<FavoriteEntity>(Extra.DATA)
            }
            when (data) {
                is ListData -> {
                    val selectedId = data.id
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
                is FavoriteEntity -> {
                    val selectedId = data.id
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
            onFavoriteClicked(data)
        }
    }


    private fun setUpObserver(movieId: String) {
        viewModel.getAllMovieFavorite()
        viewModel.isLoading.observe(this, { isLoading ->
            with(detailBinding) {
                if (isLoading) {
                    progressLoading.isVisible = true
                    contentMovieDetail.containerDetail.isVisible = false
                } else {
                    progressLoading.isVisible = false
                    contentMovieDetail.containerDetail.isVisible = true
                }
            }
        })

        viewModel.getDetailMovieFromApi(movieId).observe(this, {
            viewModel.isLoading.value = false
            if (it.message == API.MESSAGE_FAIL) {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            } else {
                detailBinding.apply {
                    textMovieName.text = it.originalTitle
                    imageMovieCover.loadUrl(API_URL_IMAGE_ORIGINAL + it.posterPath)
                    imageMovie.loadUrl(API_URL_IMAGE_W500 + it.posterPath)
                    val rate = it.voteAverage?.let { vote -> (vote * 10) / 20 }
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
            }
        })

        viewModel.getMovieCredit(movieId).observe(this, {
            val dataCreditAdapter = DataCreditAdapter(it.cast)
            with(detailBinding.contentMovieDetail) {
                val snapHelper = StartSnapHelper()
                snapHelper.attachToRecyclerView(recyclerViewCast)
                recyclerViewCast.addItemDecoration(HorizontalItemDecoration(this@DetailMovieActivity))
                recyclerViewCast.adapter = dataCreditAdapter
            }
        })

        viewModel.getMovieRecommendations(movieId).observe(this, {
            val movieRecommendationAdapter = DataRecommendationsAdapter(it.results, true)
            with(detailBinding.contentMovieDetail) {
                recyclerViewRecommendations.addItemDecoration(
                    GridItemDecoration(
                        resources.getDimensionPixelSize(
                            R.dimen.marginM
                        )
                    )
                )
                recyclerViewRecommendations.adapter = movieRecommendationAdapter
            }
        })

        viewModel.favoriteData.observe(this, { favoriteList ->
            val favoriteFiltered = favoriteList.filter { it.title == detailBinding.textMovieName.text }
            val favoriteItem = favoriteFiltered.find { it.title == detailBinding.textMovieName.text }
            isFavorite = favoriteItem?.isFavorite == 1
            if (favoriteItem?.isFavorite == 1) {
                detailBinding.imageBookmark.setImageResource(R.drawable.ic_bookmark_active)
            } else {
                detailBinding.imageBookmark.setImageResource(R.drawable.ic_bookmark_inactive)
            }
        })
    }

    private fun <T> onFavoriteClicked(data: T) = with(detailBinding) {
        imageBookmark.setOnClickListener {
            when (data) {
                is ListData -> {
                    data.isFavorite = if (isFavorite) 0 else 1
                    isFavorite = !isFavorite
                    changeStateOfImageBookmark(isFavorite)
                    viewModel.prepareDataToFavorite(data)
                    showSnackBar(isFavorite)
                }
                is FavoriteEntity -> {
                    data.isFavorite = if (isFavorite) 0 else 1
                    isFavorite = !isFavorite
                    changeStateOfImageBookmark(isFavorite)
                    viewModel.prepareDataToFavorite(data)
                    showSnackBar(isFavorite)
                }
            }
        }
    }

    private fun changeStateOfImageBookmark(isFavorite: Boolean) = with(detailBinding) {
        if (isFavorite) {
            imageBookmark.setImageResource(R.drawable.ic_bookmark_active)
        } else {
            imageBookmark.setImageResource(R.drawable.ic_bookmark_inactive)
        }
    }

    private fun showSnackBar(hasFavorite: Boolean) = with(detailBinding) {
        if (hasFavorite) {
            Snackbar.make(
                containerDetailMovie,
                getString(R.string.app_success_insert_favorite),
                Snackbar.LENGTH_SHORT
            ).show()
        } else {
            Snackbar.make(
                containerDetailMovie,
                getString(R.string.app_success_remove_favorite),
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}