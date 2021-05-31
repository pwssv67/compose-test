package ru.pwssv67.composetest.ui.layouts

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
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.pwssv67.composetest.RecordType
import ru.pwssv67.composetest.defaultLimits
import ru.pwssv67.composetest.ui.views.ElementCard
import java.util.*
import kotlin.random.Random

val shapes = Shapes(
    small = RoundedCornerShape(percent = 100),
    medium = RoundedCornerShape(10.dp),
    large = RoundedCornerShape(5.dp)
)

@Composable
fun MainLayout(
    glasses: LiveData<Int>,
    calories: LiveData<Int>,
    training: LiveData<Int>,
    limits:Map<RecordType, Int>,
    addCallback:(RecordType)->Unit,
    decreaseCallback:(RecordType)->Unit
) {
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
                        increseCallback = {type -> addCallback(type)},
                        decreaseCallback = {type -> decreaseCallback(type)}
                    )
                }
            }
        }
    }
}


private val _glasses = MutableLiveData<Int>(8)
private val _calories = MutableLiveData<Int>(1525)
private val _training = MutableLiveData<Int>(27)
private val _limits = defaultLimits
@Preview(name = "main layout preview", showSystemUi = true, device = Devices.PIXEL_4)
@Composable
private fun MainLayoutPreview() {
    MainLayout(glasses = _glasses,
        calories = _calories,
        training = _training,
        limits = _limits,
        addCallback = { /*TODO*/ },
        decreaseCallback = {}
        )
}



