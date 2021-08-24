import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.audifypracticaltest.R
import com.test.audifypracticaltest.model.Song
import kotlinx.android.synthetic.main.layout_songs_list_items.view.*
import java.util.*

abstract class SongsAdapter(private var songs: List<Song>, private var context: Context) :
    RecyclerView.Adapter<SongsAdapter.MViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_songs_list_items, parent, false)
        return MViewHolder(view)
    }

    override fun onBindViewHolder(vh: MViewHolder, position: Int) {
        vh.bind(songs[position])
        vh.itemView.imgFavourite.setOnClickListener {
            if(songs[position].isChecked == 0){
                vh.itemView.imgFavourite.setImageResource(R.drawable.ic_baseline_favorite_24)
                songs[position].isChecked = 1
            }else {
                songs[position].isChecked = 0
                vh.itemView.imgFavourite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }
        }
    }

    override fun getItemCount(): Int {
        return songs.size
    }

    fun update(data: List<Song>) {
        songs = data
        notifyDataSetChanged()
    }

    fun reverseList() {
        Collections.reverse(songs)
        notifyDataSetChanged()
    }

    class MViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textSongName: TextView = view.textSongName
        private val textSongArtist: TextView = view.textSongArtist
        private val imgFavourite: ImageView = view.imgFavourite

        fun bind(songs: Song) {
            textSongName.text = songs.aName
            textSongArtist.text = "Artist: " + songs.aArtist

        }
    }

    abstract fun addRemoveSongInFavouriteList(songName : String)
}