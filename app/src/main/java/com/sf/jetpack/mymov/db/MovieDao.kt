package com.sf.jetpack.mymov.db

import androidx.paging.DataSource
import androidx.room.*

/**
 * بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيم
 * Created By Fahmi
 */

@Dao
interface MovieDao {
    @Query("SELECT * FROM movieentities")
    fun getAllMovie(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movieentities WHERE is_bookmark = 1")
    fun getAllMovieBookmark(): DataSource.Factory<Int, MovieEntity>

    @Update
    fun updateMovieEntities(movieEntity: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movieEntity: List<MovieEntity>)

}