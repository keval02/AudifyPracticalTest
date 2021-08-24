package com.test.audifypracticaltest.room

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class FavouriteRepository {

    companion object {

        var loginDatabase: FavouriteDatabase? = null

        var loginTableModel: LiveData<FavouriteTableModel>? = null

        fun initializeDB(context: Context) : FavouriteDatabase {
            return FavouriteDatabase.getDataseClient(context)
        }

        fun insertData(context: Context, username: String) {

            loginDatabase = initializeDB(context)

            CoroutineScope(IO).launch {
                val loginDetails = FavouriteTableModel(username)
                loginDatabase!!.loginDao().InsertData(loginDetails)
            }

        }

        fun getLoginDetails(context: Context, username: String) : LiveData<FavouriteTableModel>? {

            loginDatabase = initializeDB(context)

            loginTableModel = loginDatabase!!.loginDao().getLoginDetails(username)

            return loginTableModel
        }

    }
}