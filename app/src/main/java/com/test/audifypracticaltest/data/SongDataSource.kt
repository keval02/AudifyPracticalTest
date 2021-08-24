package com.test.audifypracticaltest.data

import android.app.Application
import android.content.Context
import android.database.Cursor
import android.net.Uri
import com.test.audifypracticaltest.model.Song
import com.test.audifypracticaltest.model.SongsDataSource
import android.provider.MediaStore
import android.util.Log
import com.test.audifypracticaltest.utils.App
import java.lang.Exception


class SongDataSource() : SongsDataSource {
    override fun retrieveSongs(callback: OperationCallback<Song>) {
        val tempAudioList: MutableList<Song> = ArrayList()
        val uri: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf<String>(
            MediaStore.Audio.AudioColumns.DATA,
            MediaStore.Audio.AudioColumns.TITLE,
            MediaStore.Audio.ArtistColumns.ARTIST,
        )
        val selection = MediaStore.Audio.Media.IS_MUSIC + " != 0"
        val c: Cursor? = App.context.contentResolver.query(
            uri, projection, selection, null, null
        )
        if (c != null) {
            while (c.moveToNext()) {

                val path = c.getString(0)
                val name = c.getString(1)
                val artist = c.getString(2)
                val audioModel = Song(path, name, "", artist)

                if(path != null && (path.endsWith(".aac")
                            || path.endsWith(".mp3")
                            || path.endsWith(".wav")
                            || path.endsWith(".ogg")
                            || path.endsWith(".ac3")
                            || path.endsWith(".m4a"))) {

                    tempAudioList.add(audioModel)
                }
            }
            c.close()
        }


        callback.onSuccess(tempAudioList)
    }
}