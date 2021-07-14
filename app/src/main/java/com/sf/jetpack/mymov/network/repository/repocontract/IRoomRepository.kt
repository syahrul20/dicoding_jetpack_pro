package com.sf.jetpack.mymov.network.repository.repocontract

import com.sf.jetpack.mymov.db.FavoriteEntity

interface IRoomRepository {
    suspend fun getListFavorite(): List<FavoriteEntity>
    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity)
    suspend fun insertFavorite(favoriteEntity: FavoriteEntity)
}