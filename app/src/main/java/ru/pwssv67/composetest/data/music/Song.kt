package ru.pwssv67.composetest.data.music

import android.net.Uri
import androidx.media2.common.MediaItem

class Song(
    val title:String?,
    val album: String?,
    val author:String?,
    val imageUri: Uri?,
    val mediaItem: MediaItem
)