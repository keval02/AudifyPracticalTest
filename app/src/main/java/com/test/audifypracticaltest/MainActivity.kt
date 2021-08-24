package com.test.audifypracticaltest

import SongsAdapter
import SongsViewModel
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.audifypracticaltest.di.Injection
import com.test.audifypracticaltest.model.Song
import com.test.audifypracticaltest.room.FavouriteViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: SongsViewModel
    private lateinit var adapter: SongsAdapter
    lateinit var loginViewModel: FavouriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupUI()
    }

    private fun setupUI() {
        adapter = object : SongsAdapter(viewModel.songs.value ?: emptyList(), this){
            override fun addRemoveSongInFavouriteList(songName: String) {
                loginViewModel.insertData(this@MainActivity, songName)
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }


    //view model
    private fun setupViewModel() {

        viewModel = ViewModelProvider(
            this,
            Injection.provideViewModelFactory(applicationContext)
        ).get(SongsViewModel::class.java)


        val uri: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Audio.AudioColumns.DATA,
            MediaStore.Audio.AudioColumns.TITLE,
            MediaStore.Audio.AudioColumns.ALBUM,
            MediaStore.Audio.ArtistColumns.ARTIST
        )
        val c: Cursor? = this.contentResolver.query(
            uri,
            projection,
            MediaStore.Audio.Media.DATA + " like ? ",
            arrayOf("%utm%"),
            null
        )


        viewModel.songs.observe(this, renderVersions)
        viewModel.isViewLoading.observe(this, isViewLoadingObserver)
        viewModel.onMessageError.observe(this, onMessageErrorObserver)
        viewModel.isEmptyList.observe(this, emptyListObserver)
    }

    //observers
    private val renderVersions = Observer<List<Song>> {
        layoutEmpty.visibility = View.GONE
        adapter.update(it)
    }

    private val isViewLoadingObserver = Observer<Boolean> {
        val visibility = if (it) View.VISIBLE else View.GONE
        progressBar.visibility = visibility
    }

    private val onMessageErrorObserver = Observer<Any> {
        layoutEmpty.visibility = View.GONE
    }

    private val emptyListObserver = Observer<Boolean> {
        layoutEmpty.visibility = View.VISIBLE
    }
}