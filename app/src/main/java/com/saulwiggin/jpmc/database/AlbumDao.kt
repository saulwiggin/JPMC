package com.saulwiggin.jpmc.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.saulwiggin.jpmc.database.DatabaseAlbum

@Dao
interface AlbumDao {
    @Query("SELECT * FROM DatabaseAlbum")
    fun getLocalDBAlbums(): LiveData<List<DatabaseAlbum>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(album: List<DatabaseAlbum>)
}

@Database(entities=[DatabaseAlbum::class], version=1,exportSchema = false)
abstract class AlbumDatabase: RoomDatabase(){
    abstract val albumDao: AlbumDao
}