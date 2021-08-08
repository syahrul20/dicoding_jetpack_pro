package com.sf.jetpack.mymov.network.repository

import com.sf.jetpack.mymov.db.MovieDao
import com.sf.jetpack.mymov.network.repository.repocontract.IRoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
//
//class RoomRepository(private val favoriteDao: MovieDao) : IRoomRepository {
//    override suspend fun getListFavorite(): List<FavoriteEntity> {
//        return withContext(Dispatchers.IO) {
//            favoriteDao.getAll()
//        }
//    }
//
//    override suspend fun insertFavorite(favoriteEntity: FavoriteEntity) {
//        withContext(Dispatchers.IO) {
//            favoriteDao.insert(favoriteEntity)
//        }
//    }
//
//    override suspend fun deleteFavorite(favoriteEntity: FavoriteEntity) {
//        withContext(Dispatchers.IO) {
//            favoriteDao.delete(favoriteEntity)
//        }
//    }
//}