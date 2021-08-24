package com.test.audifypracticaltest.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.test.audifypracticaltest.model.Song

@Database(entities = arrayOf(FavouriteTableModel::class), version = 1, exportSchema = false)
abstract class FavouriteDatabase : RoomDatabase() {

    abstract fun loginDao() : DAOAccess

    companion object {

        @Volatile
        private var INSTANCE: FavouriteDatabase? = null

        fun getDataseClient(context: Context) : FavouriteDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, FavouriteDatabase::class.java, "FAVOURITE_DATABASE")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }

}