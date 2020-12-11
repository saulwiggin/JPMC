package com.saulwiggin.jpmc.network

import com.saulwiggin.jpmc.database.DatabaseAlbum
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiServices {
    @GET(API_Calls.API_ALBUMS_LIST)
    fun getAlbum(): Deferred<List<DatabaseAlbum>>
}