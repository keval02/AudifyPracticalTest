import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.audifypracticaltest.model.SongsRepository

class ViewModelFactory(private val repository: SongsRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SongsViewModel(repository) as T
    }
}