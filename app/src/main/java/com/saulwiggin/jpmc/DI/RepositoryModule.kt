package com.saulwiggin.jpmc.DI


import com.saulwiggin.jpmc.database.AlbumDatabase
import com.saulwiggin.jpmc.network.ApiServices
import com.saulwiggin.jpmc.repository.AlbumRepository
import org.koin.dsl.module

val repositoryModule = module {
    fun provideRepository(api: ApiServices, dao: AlbumDatabase): AlbumRepository {
        return AlbumRepository(api, dao)
    }

    single {
        provideRepository(get(), get())
    }
}