package com.sf.jetpack.mymov.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

/**
 * بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيم
 * Created By Fahmi
 */

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorites")
    fun getAll(): List<FavoriteEntity>

    @Query("SELECT * FROM favorites WHERE type LIKE 'MOVIE'")
    fun getAllMovieFavorite(): PagingSource<Int, FavoriteEntity>

    @Query("SELECT * FROM favorites WHERE type LIKE 'TV_SHOW'")
    fun getAllTvShowFavorite(): PagingSource<Int, FavoriteEntity>

    @Insert
    fun insert(vararg favorite: FavoriteEntity)

    @Delete
    fun delete(favorite: FavoriteEntity)
}