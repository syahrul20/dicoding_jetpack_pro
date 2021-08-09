package com.sf.jetpack.mymov.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.sf.jetpack.mymov.BuildConfig
import com.sf.jetpack.mymov.R
import com.sf.jetpack.mymov.adapter.DataCreditAdapter
import com.sf.jetpack.mymov.adapter.DataRecommendationsAdapter
import com.sf.jetpack.mymov.databinding.ActivityDetailTvShowBinding
import com.sf.jetpack.mymov.db.TvShowEntity
import com.sf.jetpack.mymov.network.response.TvResultList
import com.sf.jetpack.mymov.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.ClassCastException

/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By Fahmi
 */

class DetailTvShowActivity : AppCompatActivity() {

    private lateinit var detailBinding: ActivityDetailTvShowBinding
    private val viewModel: DetailViewModel by viewModel()
    private var isFavorite: Boolean = false

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
            val data = extras.getParcelable<TvShowEntity>(Extra.DATA)
            when (data) {
                is TvShowEntity -> {
                    val selectedId = data.id
                    setUpObserver(selectedId.toString())
                    with(detailBinding) {
                        textTvShowName.text = data.title
                        imageTvShowCover.loadUrl(BuildConfig.API_URL_IMAGE_ORIGINAL + data.poster_path)
                        imageTvShow.loadUrl(BuildConfig.API_URL_IMAGE_W500 + data.poster_path)
                        val rate = data.vote_average.let { (it * 10) / 20 }
                        ratingBar.rating = rate.toFloat()
                        textRating.text = getString(R.string.app_movie_rating_count, rate)
                    }
                }
            }
            onFavoriteClicked(data)
        }
    }

    private fun setUpActionBar() {
        setSupportActionBar(detailBinding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    private fun setUpObserver(tvShowId: String) {
        viewModel.isLoading.observe(this, { isLoading ->
            with(detailBinding) {
                if (isLoading) {
                    progressLoading.isVisible = true
                    contentTvShowDetail.containerDetail.isVisible = false
                } else {
                    progressLoading.isVisible = false
                    contentTvShowDetail.containerDetail.isVisible = true
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
                    imageTvShowCover.loadUrl(BuildConfig.API_URL_IMAGE_ORIGINAL + it.posterPath)
                    imageTvShow.loadUrl(BuildConfig.API_URL_IMAGE_W500 + it.posterPath)
                    val rate = it.voteAverage?.let { vote -> (vote * 10) / 20 }
                    ratingBar.rating = rate?.toFloat() ?: 0F
                    textRating.text = getString(R.string.app_movie_rating_count, rate)
                    with(this.contentTvShowDetail) {
                        if (it.overview.isEmpty()) {
                            textOverview.isVisible = false
                            textOverviewNotFound.isVisible = true
                        } else {
                            textOverview.isVisible = true
                            textOverview.text = it.overview
                        }
                        val genres = ArrayList<String>()
                        it.genres.forEach { item ->
                            genres.add(item.name)
                        }
                        textGenre.text = genres.joinToString()
                        textReleaseDate.text =
                            convertDate(it.firstAirDate, "yyyy-MM-dd", "dd MMM yyyy")
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
            val tvShowRecommendationAdapter = DataRecommendationsAdapter(it.results, false)
            with(detailBinding.contentTvShowDetail) {
                if (it.results.isEmpty()) {
                    textRecommendationEmpty.isVisible = true
                    recyclerViewRecommendations.isVisible = false
                } else {
                    textRecommendationEmpty.isVisible = false
                    recyclerViewRecommendations.isVisible = true
                    recyclerViewRecommendations.addItemDecoration(
                        GridItemDecoration(
                            resources.getDimensionPixelSize(
                                R.dimen.marginM
                            )
                        )
                    )
                    recyclerViewRecommendations.adapter = tvShowRecommendationAdapter
                }
            }
        })

        viewModel.favoriteData.observe(this, { favoriteList ->
            val favoriteFiltered = favoriteList.filter { it.title == detailBinding.textTvShowName.text }
            val favoriteItem =
                favoriteFiltered.find { it.title == detailBinding.textTvShowName.text }
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
                is TvShowEntity -> {
                    data.isFavorite = if (isFavorite) 0 else 1
                    isFavorite = !isFavorite
                    changeStateOfImageBookmark(isFavorite)
//                    viewModel.prepareDataToFavorite(data)
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
                containerDetailTvShow,
                getString(R.string.app_success_insert_favorite),
                Snackbar.LENGTH_SHORT
            ).show()
        } else {
            Snackbar.make(
                containerDetailTvShow,
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