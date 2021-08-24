package com.test.audifypracticaltest.model

import android.content.Context
import com.test.audifypracticaltest.data.OperationCallback

interface SongsDataSource {
    fun retrieveSongs(callback: OperationCallback<Song>)
}