package ru.pwssv67.composetest.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import org.koin.android.ext.android.inject
import ru.pwssv67.composetest.RecordType
import ru.pwssv67.composetest.ui.layouts.MainLayout
import ru.pwssv67.composetest.ui.viewmodels.HealthCounterViewModel
import ru.pwssv67.composetest.ui.views.ElementCard
import java.util.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val healthCounterViewModel:HealthCounterViewModel by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainLayout(
                glasses = healthCounterViewModel.glasses,
                calories = healthCounterViewModel.calories,
                training = healthCounterViewModel.training,
                limits = healthCounterViewModel.limits,
                {type -> addWithType(type)},
                {type -> decreaseWithType(type)}
            )
        }
    }

    private fun addWithType(elementType: RecordType) {
        healthCounterViewModel.addWithType(elementType)
    }

    private fun decreaseWithType(elementType: RecordType) {
        healthCounterViewModel.decreaseWithType(elementType)
    }



}