package com.saulwiggin.jpmc.repository

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.saulwiggin.jpmc.database.AlbumDao
import com.saulwiggin.jpmc.database.AlbumDatabase
import org.junit.After
import org.junit.Before
import java.lang.Exception

open class DatabaseTest {
    // system under test
    protected lateinit var database: AlbumDatabase
    val albumDao: AlbumDao
        get() = database.albumDao

    @Before
    open fun init() {
        //Create a temporally database
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AlbumDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @After
    fun finish() {
        try {
            database.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}