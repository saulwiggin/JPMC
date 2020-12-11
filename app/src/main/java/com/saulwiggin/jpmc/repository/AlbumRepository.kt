package com.saulwiggin.jpmc.repository

import androidx.lifecycle.LiveData
import com.saulwiggin.jpmc.database.AlbumDatabase
import com.saulwiggin.jpmc.database.DatabaseAlbum
import com.saulwiggin.jpmc.network.ApiServices
import com.saulwiggin.jpmc.utils.OpenForTesting
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OpenForTesting
class AlbumRepository(private val ApiServices: ApiServices, private val database: AlbumDatabase) {
    suspend fun refresh(){
        // worker thread to perform API request and save data locally
        withContext(Dispatchers.IO){
            val albumList = ApiServices.getAlbum().await()
            database.albumDao.insertAll(albumList)
        }
    }

    val results: LiveData<List<DatabaseAlbum>> = database.albumDao.getLocalDBAlbums()

}