package ru.pwssv67.composetest.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.android.ext.android.inject
import ru.pwssv67.composetest.RecordType
import ru.pwssv67.composetest.ui.layouts.MainLayout
import ru.pwssv67.composetest.ui.viewmodels.HealthCounterViewModel
import ru.pwssv67.composetest.ui.viewmodels.HomeMusicViewModel
import ru.pwssv67.composetest.ui.views.ElementCard
import ru.pwssv67.composetest.ui.views.SongCard
import java.util.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val healthCounterViewModel:HealthCounterViewModel by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShowMusicLayout()
            /*
            MainLayout(
                glasses = healthCounterViewModel.glasses,
                calories = healthCounterViewModel.calories,
                training = healthCounterViewModel.training,
                limits = healthCounterViewModel.limits,
                {type -> addWithType(type)},
                {type -> decreaseWithType(type)}
            )
             */
        }
    }

    private fun addWithType(elementType: RecordType) {
        healthCounterViewModel.addWithType(elementType)
    }

    private fun decreaseWithType(elementType: RecordType) {
        healthCounterViewModel.decreaseWithType(elementType)
    }

    @Preview(device = Devices.PIXEL_4, showSystemUi = true)
    @Composable
    fun ShowMusicLayout() {
        val viewModel = viewModel(modelClass = HomeMusicViewModel::class.java)
        val state = viewModel.state.collectAsState()

        Column() {
            LazyColumn(
                modifier = Modifier.padding(8.dp).fillMaxHeight(6f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(state.value.songs) {
                    SongCard(song = it, onMoreClick = { /*TODO*/ }, onCardCallback = {})
                }
            }

            Row(Modifier.fillMaxWidth().fillMaxHeight(1f).background(Color.Gray)) {
                Text("sfsd")
            }
        }
    }



}