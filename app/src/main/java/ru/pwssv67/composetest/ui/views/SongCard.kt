package ru.pwssv67.composetest.ui.views


import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toFile
import androidx.media2.common.MediaItem
import androidx.media2.common.MediaMetadata
import com.google.accompanist.coil.rememberCoilPainter
import ru.pwssv67.composetest.R
import ru.pwssv67.composetest.data.music.Song
import java.io.File

@Composable
fun SongCard(song: Song, onMoreClick: (Song) -> Unit, onCardCallback: (Song) -> Unit) {
    Surface(
        Modifier
            .background(Color.White)
        ,
        elevation = 4.dp

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onCardCallback(song) }
                .padding(4.dp, 4.dp)
                .wrapContentHeight()
        ) {
            Row {
                val image = if (song.imageUri != null && song.imageUri != Uri.EMPTY)
                    rememberCoilPainter(request = song.imageUri.path)
                else
                    painterResource(id = R.drawable.ic_baseline_image_24)
                Image(
                    image,
                    null,
                    modifier = Modifier.size(56.dp)
                )
                Column(
                    modifier = Modifier
                        .height(IntrinsicSize.Max)
                        .width(IntrinsicSize.Max),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = song.title ?: "ooo",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.W300
                    )
                    Text(
                        text = song.author ?: "authooor",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W300
                    )
                }
            }
            Image(
                painterResource(id = R.drawable.ic_baseline_more_vert_24),
                null,
                modifier = Modifier
                    .size(40.dp)
                    .clickable { onMoreClick(song) }
                    .padding(8.dp),
                colorFilter = ColorFilter.tint(Color.Gray)
            )
        }

    }
}

private val testItem = Song(
    "Take Me In",
    "Album",
    "Author",
    Uri.EMPTY,
    MediaItem.Builder().build()
)

val items = ArrayList<Song>()

fun getTestItems(): ArrayList<Song> {
    val tempItems = ArrayList<Song>()
    for (i in 0..10) {
        tempItems.add(testItem)
    }
    return tempItems
}


@Preview(device = Devices.PIXEL_4, showSystemUi = true)
@Composable
private fun SongCardPreview() {
    val items: ArrayList<Song> = remember { getTestItems() }
    Scaffold() {
        Surface {
            LazyColumn(
                modifier = Modifier.padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(items) { item ->
                    SongCard(
                        song = item,
                        {},
                        {}
                    )
                }
            }
        }
    }

}