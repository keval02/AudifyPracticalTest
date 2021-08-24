package com.test.audifypracticaltest.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Favourite")
data class FavouriteTableModel (

    @ColumnInfo(name = "songName")
    var SongName: String

) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Int? = null

}