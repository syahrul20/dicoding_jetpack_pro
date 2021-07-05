package com.sf.jetpack.mymov.db

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

    @Insert
    fun insert(vararg favorite: FavoriteEntity)

    @Delete
    fun delete(favorite: FavoriteEntity)
}