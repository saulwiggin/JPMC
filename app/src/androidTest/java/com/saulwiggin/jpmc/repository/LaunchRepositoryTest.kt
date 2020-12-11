package com.saulwiggin.jpmc.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.saulwiggin.jpmc.network.ApiServices
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class LaunchRepositoryTest : DatabaseTest() {

    private lateinit var mRepo: AlbumRepository

    @Mock
    lateinit var apiService: ApiServices

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    override fun init() {
        super.init()
        MockitoAnnotations.initMocks(this);

        mRepo = AlbumRepository(
            apiService,
            database
        )
    }

    @Test
    fun test_launches_retrieve_expected_data() =
        runBlocking {
            val fakeToReturn =
                CompletableDeferred(FakeObjectsUtils.listDBDatabaseLaunches)

        Mockito.`when`(apiService.getAlbum()).thenReturn(fakeToReturn)

            val dataReceived = mRepo.refresh()

            assertNotNull(dataReceived)
        }

}