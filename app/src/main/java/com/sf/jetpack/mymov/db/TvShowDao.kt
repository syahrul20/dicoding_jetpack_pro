package com.sf.jetpack.mymov.db

import androidx.paging.DataSource
import androidx.room.*

@Dao
interface TVShowDao {
    @Query("SELECT * FROM tvshowentities")
    fun getAllTvShow(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tvshowentities WHERE is_favorite = 1")
    fun getAllTvShowFavorite(): DataSource.Factory<Int, TvShowEntity>

    @Update
    fun updateTvShowEntities(tvShowEntity: TvShowEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvshowEntity: List<TvShowEntity>)
}