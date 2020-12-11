package com.saulwiggin.jpmc.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MediatorLiveData
import com.saulwiggin.jpmc.repository.AlbumRepository
import com.saulwiggin.jpmc.ui.main.MainViewModel
import com.saulwiggin.jpmc.utils.LoadingState
import com.saulwiggin.jpmc.MainCoroutineRule
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class AlbumListViewmodelTest {
    @Mock
    private lateinit var mRepo: AlbumRepository

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    lateinit var launchListViewModel: MainViewModel

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun test_refreshDataFromRepository_do_what_it_should_do() = runBlocking {

        //prep
        launchListViewModel = MainViewModel(mRepo)

        val liveDataTestUtil = LiveDataTestUtil<LoadingState>()
        //action
        //when launchListViewModel is created refreshLaunch is called.
        //verify
        Mockito.verify(mRepo).refresh()
        val dataEmitted = liveDataTestUtil.getValue(launchListViewModel.loadingState)
        assertEquals( LoadingState.Status.SUCCESS.name,dataEmitted?.status?.name)
    }

    @Test
    fun test_loading_error_is_called() = runBlocking {
        //prep
        //action
        //when charactersListViewModel is created refreshDataFromRepository is called.
        //verify
        Mockito.`when`(mRepo.refresh()).thenThrow(RuntimeException())
        launchListViewModel = MainViewModel(mRepo)
        val mediatorLiveData = MediatorLiveData<LoadingState>()
        mediatorLiveData.addSource(launchListViewModel.loadingState){
            when(it.status){
                LoadingState.Status.RUNNING -> {
                    //ignore
                }
                else -> {
                    assertEquals( LoadingState.Status.FAILED.name,it?.status?.name)

                }
            }
        }
    }
}