package ru.pwssv67.composetest.ui.viewmodels

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.media2.common.MediaItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.pwssv67.composetest.data.music.Song

class HomeMusicViewModel : ViewModel() {
    private val _state = MutableStateFlow(
        HomeMusicState(null, ArrayList())
    )
    val state:StateFlow<HomeMusicState>
        get() = _state.asStateFlow()

    init {
        val songList = ArrayList<Song>()
        (0..15).forEach {
            songList.add(
                Song(
                    "title #$it",
                    "album #$it",
                    "author #$it",
                    Uri.EMPTY,
                    MediaItem.Builder().build()
                )
            )
        }
        _state.value = _state.value.copy(songs = songList)
    }
}

data class HomeMusicState(
    val currentSong: Song?,
    val songs: ArrayList<Song>
)