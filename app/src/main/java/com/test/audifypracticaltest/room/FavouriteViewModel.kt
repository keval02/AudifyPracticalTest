package com.test.audifypracticaltest.room

import android.content.Context
import androidx.lifecycle.LiveData

class FavouriteViewModel {
    var liveDataLogin: LiveData<FavouriteTableModel>? = null

    fun insertData(context: Context, username: String) {
        FavouriteRepository.insertData(context, username)
    }

    fun getLoginDetails(context: Context, username: String) : LiveData<FavouriteTableModel>? {
        liveDataLogin = FavouriteRepository.getLoginDetails(context, username)
        return liveDataLogin
    }
}