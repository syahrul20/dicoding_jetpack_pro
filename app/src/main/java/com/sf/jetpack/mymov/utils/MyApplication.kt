package com.sf.jetpack.mymov.utils

import android.app.Application
import com.sf.jetpack.mymov.db.AppDatabase
import com.sf.jetpack.mymov.db.MovieDao
import com.sf.jetpack.mymov.db.TVShowDao
import com.sf.jetpack.mymov.detail.DetailViewModel
import com.sf.jetpack.mymov.fragment.movie.MovieViewModel
import com.sf.jetpack.mymov.fragment.tvshow.TvShowViewModel
import com.sf.jetpack.mymov.network.ApiService
import com.sf.jetpack.mymov.network.datasource.MovieDataSource
import com.sf.jetpack.mymov.network.datasource.TvDataSource
import com.sf.jetpack.mymov.network.repository.*
import com.sf.jetpack.mymov.network.repository.repocontract.*
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
                    appExecutors,
                    httpModule,
                    retrofitModule,
                    repositoryModule,
                    databaseModule,
                    viewModelModule
                )
            )
        }
    }

    private val appExecutors = module {
        single { AppExecutors() }
    }

    private val httpModule = module {
        single { ApiService.httpClient(androidContext()) }
    }

    private val databaseModule = module {
        fun movieDao(database: AppDatabase): MovieDao {
            return database.movieDao()
        }
        fun tvShowDao(database: AppDatabase): TVShowDao {
            return database.tvShowDao()
        }
        single { AppDatabase.getInstance(androidContext()) }
        single { movieDao(get()) }
        single { tvShowDao(get()) }
    }

    private val retrofitModule = module {
        single { ApiService.apiRequest<MovieDataSource>(get()) }
        single { ApiService.apiRequest<TvDataSource>(get()) }
    }
    private val repositoryModule = module {
        single<IMovieRepository> { MovieRepository(get(), get(), get()) }
        single<ITvRepository> { TvRepository(get()) }
//        single<IRoomRepository> { RoomRepository(get()) }
    }
    private val viewModelModule = module {
        viewModel { MovieViewModel(get()) }
        viewModel { TvShowViewModel(get()) }
        viewModel { DetailViewModel(get(), get(), get()) }
    }
}
