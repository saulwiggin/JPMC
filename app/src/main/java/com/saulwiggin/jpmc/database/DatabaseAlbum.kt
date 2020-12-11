package com.saulwiggin.jpmc.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DatabaseAlbum (
    @PrimaryKey
    var id: Int,
    var userId: Int,
    var title: String
)