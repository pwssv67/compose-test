package ru.pwssv67.composetest.ui.views


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media2.common.MediaItem
import androidx.media2.common.MediaMetadata
import ru.pwssv67.composetest.R

@Composable
fun SongCard(mediaItem: MediaItem, onCardClickCallback:(MediaItem)->Unit, onPlayPauseClickCallback:(MediaItem)->Unit) {
    Surface(
        Modifier
            .background(Color.White),
        elevation = 4.dp
    ) {
        Row(
            verticalAlignment =  Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp, 4.dp)
        ) {
            Row {
                Image(
                    painterResource(id = R.drawable.ic_baseline_image_24),
                    null,
                    modifier = Modifier.size(48.dp)
                )
                Column(verticalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.wrapContentHeight()) {
                    Text(
                        text = mediaItem.metadata?.getString(MediaMetadata.METADATA_KEY_TITLE)
                            ?: "",
                        fontSize = 20.sp
                    )
                    Text(
                        text = mediaItem.metadata?.getString(MediaMetadata.METADATA_KEY_ARTIST)
                            ?: "",
                        fontSize = 14.sp
                    )
                }
            }
            Image(painterResource(id = R.drawable.ic_baseline_fire_48), null, modifier = Modifier.size(40.dp))
        }

    }
}

private val testItem = MediaItem.Builder()
    .setMetadata(
        MediaMetadata.Builder()
            .putString(MediaMetadata.METADATA_KEY_ALBUM, "Sample Album")
            .putString(MediaMetadata.METADATA_KEY_ARTIST, "Artist")
            .putString(MediaMetadata.METADATA_KEY_TITLE, "Song")
            //.putBitmap(MediaMetadata.METADATA_KEY_ALBUM_ART, Bitmap.crea)
            .build()
    ).build()

val items = ArrayList<MediaItem>()

fun getTestItems(): ArrayList<MediaItem> {
    val tempItems = ArrayList<MediaItem>()
    for (i in 0..10) {
        tempItems.add(testItem)
    }
    return tempItems
}


@Preview(device = Devices.NEXUS_5, showSystemUi = true)
@Composable
private fun SongCardPreview() {
    val items:ArrayList<MediaItem> = remember { getTestItems() }
    Scaffold() {
        Surface {
            LazyColumn(
                modifier = Modifier.padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(items) { item ->
                    SongCard(
                        mediaItem = item,
                        {},
                        {}
                    )
                }
            }
        }
    }

}