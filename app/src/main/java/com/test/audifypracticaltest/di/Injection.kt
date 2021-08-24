package com.test.audifypracticaltest.di

import ViewModelFactory
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.test.audifypracticaltest.MainActivity
import com.test.audifypracticaltest.data.SongDataSource
import com.test.audifypracticaltest.model.SongsDataSource
import com.test.audifypracticaltest.model.SongsRepository

object Injection {
    private val songDataSource: SongsDataSource = SongDataSource()
    private val songsRepository = SongsRepository(songDataSource)
    private val songsViewModelFactory = ViewModelFactory(songsRepository)

    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
        return songsViewModelFactory
    }
}