package com.test.audifypracticaltest.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DAOAccess {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertData(favouriteTableModel: FavouriteTableModel)

    @Query("SELECT * FROM Favourite WHERE SongName =:songName")
    fun getLoginDetails(songName: String?) : LiveData<FavouriteTableModel>

}