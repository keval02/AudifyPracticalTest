package com.test.audifypracticaltest.model

import java.io.Serializable


data class Song(var aPath: String, var aName: String, var aAlbum: String, var aArtist: String, var isChecked : Int = 0) : Serializable