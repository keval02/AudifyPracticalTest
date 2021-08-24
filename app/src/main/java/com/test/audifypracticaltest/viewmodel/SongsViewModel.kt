import android.app.Application
import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.audifypracticaltest.data.OperationCallback
import com.test.audifypracticaltest.model.Song
import com.test.audifypracticaltest.model.SongsRepository

class SongsViewModel(private val repository: SongsRepository) : ViewModel() {

    private val _songs = MutableLiveData<List<Song>>().apply { value = emptyList() }
    val songs: LiveData<List<Song>> = _songs

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError

    private val _isEmptyList = MutableLiveData<Boolean>()
    val isEmptyList: LiveData<Boolean> = _isEmptyList

    init {
        loadSongs()
    }

    fun loadSongs() {
        _isViewLoading.value = true
        repository.fetchSongs(object : OperationCallback<Song> {
            override fun onError(error: String?) {
                _isViewLoading.value = false
                _onMessageError.value = error
            }

            override fun onSuccess(data: List<Song>?) {
                _isViewLoading.value = false
                if (data.isNullOrEmpty()) {
                    _isEmptyList.value = true
                } else {
                    _songs.value = data
                }
            }
        }, )
    }
}