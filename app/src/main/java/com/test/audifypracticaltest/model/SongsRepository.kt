package com.test.audifypracticaltest.model

import android.content.Context
import com.test.audifypracticaltest.data.OperationCallback


class SongsRepository(private val versionDataSource: SongsDataSource) {
    fun fetchSongs(callback: OperationCallback<Song>) {
        versionDataSource.retrieveSongs(callback)
    }
}