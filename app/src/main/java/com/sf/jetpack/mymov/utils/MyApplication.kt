package com.sf.jetpack.mymov.utils

import android.app.Application
import com.sf.jetpack.mymov.detail.DetailViewModel
import com.sf.jetpack.mymov.fragment.movie.MovieViewModel
import com.sf.jetpack.mymov.fragment.tvshow.TvShowViewModel
import com.sf.jetpack.mymov.network.ApiService
import com.sf.jetpack.mymov.network.datasource.MovieDataSource
import com.sf.jetpack.mymov.network.datasource.TvDataSource
import com.sf.jetpack.mymov.network.repository.MovieRepository
import com.sf.jetpack.mymov.network.repository.TvRepository
import com.sf.jetpack.mymov.network.repository.repocontract.IMovieRepository
import com.sf.jetpack.mymov.network.repository.repocontract.ITvRepository
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(
                listOf(
                    httpModule,
                    retrofitModule,
                    repositoryModules,
                    viewModelModules
                )
            )
        }
    }

    private val httpModule = module {
        single { ApiService.httpClient(androidContext()) }
    }

    private val retrofitModule = module {
        single { ApiService.apiRequest<MovieDataSource>(get()) }
        single { ApiService.apiRequest<TvDataSource>(get()) }
    }
    private val repositoryModules = module {
        single<IMovieRepository> { MovieRepository(get()) }
        single<ITvRepository> { TvRepository(get()) }
    }
    private val viewModelModules = module {
        viewModel { MovieViewModel(get()) }
        viewModel { TvShowViewModel(get()) }
        viewModel { DetailViewModel(get(), get()) }
    }
}