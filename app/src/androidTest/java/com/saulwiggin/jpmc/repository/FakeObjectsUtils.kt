package com.saulwiggin.jpmc.repository

import com.saulwiggin.jpmc.database.DatabaseAlbum


object FakeObjectsUtils{
val DBDatabaseLaunches = DatabaseAlbum(id = 1,  userId = 10 , title = "Sgt. Pepper's Lonely Heart Club's Band")

val listDBDatabaseLaunches = listOf(DBDatabaseLaunches)
}