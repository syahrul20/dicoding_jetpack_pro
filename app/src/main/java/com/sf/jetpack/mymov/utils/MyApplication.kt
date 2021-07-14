package com.sf.jetpack.mymov.utils

import android.app.Application
import com.sf.jetpack.mymov.db.AppDatabase
import com.sf.jetpack.mymov.db.FavoriteDao
import com.sf.jetpack.mymov.detail.DetailViewModel
import com.sf.jetpack.mymov.fragment.movie.MovieViewModel
import com.sf.jetpack.mymov.fragment.tvshow.TvShowViewModel
import com.sf.jetpack.mymov.network.ApiService
import com.sf.jetpack.mymov.network.datasource.MovieDataSource
import com.sf.jetpack.mymov.network.datasource.TvDataSource
import com.sf.jetpack.mymov.network.repository.*
import com.sf.jetpack.mymov.network.repository.repocontract.*
import org.koin.android.ext.android.get
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
                    repositoryModule,
                    databaseModule,
                    viewModelModule
                )
            )
        }
    }

    private val httpModule = module {
        single { ApiService.httpClient(androidContext()) }
    }

    private val databaseModule = module {
        fun favoriteDao(database: AppDatabase): FavoriteDao {
            return database.favoriteDao()
        }
        single { AppDatabase.getInstance(androidContext()) }
        single { favoriteDao(get()) }
    }

    private val retrofitModule = module {
        single { ApiService.apiRequest<MovieDataSource>(get()) }
        single { ApiService.apiRequest<TvDataSource>(get()) }
    }
    private val repositoryModule = module {
        single<IMovieRepository> { MovieRepository(get(), get()) }
        single<IMoviePagingRepository> { MoviePagingRepository(get(), get()) }
        single<ITvShowPagingRepository> { TvShowPagingRepository(get(), get()) }
        single<ITvRepository> { TvRepository(get()) }
        single<IRoomRepository> { RoomRepository(get()) }
    }
    private val viewModelModule = module {
        viewModel { MovieViewModel(get(), get(),get()) }
        viewModel { TvShowViewModel(get(), get(),get()) }
        viewModel { DetailViewModel(get(), get(), get()) }
    }
}
