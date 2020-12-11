package com.saulwiggin.jpmc

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.saulwiggin.jpmc.database.AlbumDao
import com.saulwiggin.jpmc.database.AlbumDatabase
import com.saulwiggin.jpmc.database.DatabaseAlbum
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.saulwiggin.jpmc.utils.getOrAwaitValue



@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class DaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AlbumDatabase
    private lateinit var dao: AlbumDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AlbumDatabase::class.java
        ).allowMainThreadQueries().build()
        dao =database.albumDao
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertAlbum() = runBlocking{
        val albumItem = DatabaseAlbum(1, 10,"Sgt. Pepper's Lonely Heart Club's Band")

        dao.insertAll(listOf(albumItem))

        val allJoke = dao.getLocalDBAlbums().getOrAwaitValue()

        assertThat(allJoke).contains(albumItem)
    }
}