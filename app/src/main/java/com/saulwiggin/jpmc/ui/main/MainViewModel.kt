package com.saulwiggin.jpmc.ui.main

import androidx.lifecycle.ViewModel
import com.saulwiggin.jpmc.utils.OpenForTesting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.saulwiggin.jpmc.repository.AlbumRepository
import com.saulwiggin.jpmc.utils.LoadingState
import kotlinx.coroutines.*
import java.lang.Exception

@OpenForTesting
class MainViewModel(val albumrepository: AlbumRepository): ViewModel() {

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    private val viewModelJob = SupervisorJob()

    private val viewModelScope  = CoroutineScope(viewModelJob + Dispatchers.Main)

    val albumsResults = albumrepository.results

    init {
        refreshFromRepository()
    }

    fun refreshFromRepository() {
        viewModelScope.launch {
            try {
                _loadingState.value = LoadingState.LOADING
                albumrepository.refresh()
                _loadingState.value = LoadingState.LOADED
            } catch (networkError: Exception){
                _loadingState.value = LoadingState.error(networkError.message)
            }
        }
    }

    override fun onCleared(){
        super.onCleared()
        viewModelScope.cancel()
    }
}