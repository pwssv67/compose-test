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
import ru.pwssv67.composetest.RecordType
import ru.pwssv67.composetest.ui.views.ElementCard
import java.util.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    val shapes = Shapes(
        small = RoundedCornerShape(percent = 100),
        medium = RoundedCornerShape(10.dp),
        large = RoundedCornerShape(5.dp)
    )

    val glasses = MutableLiveData(7)
    val calories = MutableLiveData(2400)
    val training = MutableLiveData(25)

    val limits = mapOf(
        RecordType.WATER_GLASSES to 8,
        RecordType.CALORIES to 2400,
        RecordType.TRAINING_MINUTES to 30
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainLayout()
        }
    }

    @Preview(
        showBackground = true,
        backgroundColor = 0xFFFFFFFF, device = Devices.PIXEL_4, name = "main", showSystemUi = true
    )
    @Composable
    private fun MainLayout() {
        val stateGlasses: Int by glasses.observeAsState(0)
        val stateCalories: Int by calories.observeAsState(0)
        val stateTraining: Int by training.observeAsState(0)
        val random = Random(Date().time)
        MaterialTheme(shapes = shapes) {
            Surface(color = MaterialTheme.colors.surface, modifier = Modifier.fillMaxHeight()) {
                Column {
                    Card(
                        Modifier.fillMaxWidth(),
                        elevation = 8.dp,
                        shape = RoundedCornerShape(
                            topStart = 0.dp, topEnd = 0.dp,
                            bottomEnd = 5.dp, bottomStart = 5.dp
                        )
                    ) {
                        Column(Modifier.padding(16.dp, 24.dp)) {
                            Text("Ты выполняешь цели уже три дня подряд!", fontSize = 24.sp)
                            Spacer(modifier = Modifier.size(8.dp))
                            Text("Keep this up!", fontSize = 16.sp)
                        }
                    }

                    Spacer(Modifier.size(36.dp))

                    listOf(
                        RecordType.WATER_GLASSES,
                        RecordType.CALORIES,
                        RecordType.TRAINING_MINUTES
                    ).map {
                        ElementCard(
                            elementType = it, when (it) {
                                RecordType.WATER_GLASSES -> stateGlasses
                                RecordType.CALORIES -> stateCalories
                                RecordType.TRAINING_MINUTES -> stateTraining
                            },
                            limits = limits,
                            increseCallback = {type -> addWithType(type)},
                            decreaseCallback = {type -> decreaseWithType(type)}
                        )
                    }
                }
            }
        }
    }



    private fun addWithType(elementType: RecordType) {
        when (elementType) {
            RecordType.WATER_GLASSES -> glasses.postValue(glasses.value?.plus(1))
            RecordType.CALORIES -> calories.postValue(calories.value?.plus(1))
            RecordType.TRAINING_MINUTES -> training.postValue(training.value?.plus(1))
            else -> glasses.postValue(glasses.value?.plus(1))
        }
    }

    private fun decreaseWithType(elementType: RecordType) {
        when (elementType) {
            RecordType.WATER_GLASSES -> glasses.postValue(glasses.value?.minus(1))
            RecordType.CALORIES -> calories.postValue(calories.value?.minus(1))
            RecordType.TRAINING_MINUTES -> training.postValue(training.value?.minus(1))
            else -> glasses.postValue(glasses.value?.minus(1))
        }
    }

}