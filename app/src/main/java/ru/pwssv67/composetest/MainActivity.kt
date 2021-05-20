package ru.pwssv67.composetest

import android.os.Bundle
import android.transition.Transition
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintSet
import androidx.lifecycle.MutableLiveData
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
                            }
                        )
                    }
                }
            }
        }
    }


    @Preview
    @Composable
    private fun PreviewCaloriesViewCard() {
        Surface(Modifier.background(MaterialTheme.colors.background)) {
            ElementCard(elementType = RecordType.WATER_GLASSES, 40)
        }
    }

    @Composable
    fun AddRemoveCard(number: Int, elementType: RecordType) {
        val transition = updateTransition(
            number,
            label = "kek"
        )
        val color by transition.animateColor(label = "color card animation",
            transitionSpec = { spring(3f) }
        ) { number ->
            if (number >= limits[elementType]!!)
                colorResource(id = R.color.colorPrimaryDark)
            else
                colorResource(id = R.color.colorPrimary)
        }

        val textColor by transition.animateColor(label = "text color card animation",
            transitionSpec = { spring(3f) }
        ) { number ->
            if (number >= limits[elementType]!!)
                colorResource(id = R.color.white)
            else
                colorResource(id = R.color.primaryText)
        }

        val elevation by transition.animateDp(
            label = "lol",
            transitionSpec = { spring(1.5f)}
        ) { number ->
            if (number >= limits[elementType]!!)
                0.dp
            else
                8.dp
        }

        val backgroundModifier: Modifier = Modifier.background(color = color)
        Card(
            elevation = 8.dp,
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = backgroundModifier.padding(8.dp, 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(painterResource(id = R.drawable.ic_baseline_remove_24), "remove",
                    modifier = Modifier
                        .size(48.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(
                                bounded = false
                            )
                        )
                        { decreaseWithType(elementType) },
                    colorFilter = ColorFilter.tint(textColor)
                )
                Text(number.toString(), fontSize = 36.sp, color = textColor)
                Image(painterResource(id = R.drawable.ic_baseline_add_24), "add",
                    modifier = Modifier
                        .size(48.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(
                                bounded = false
                            )
                        ) { addWithType(elementType) },
                    colorFilter = ColorFilter.tint(textColor)
                )
            }
        }
    }

    @Composable
    fun ElementCard(elementType: RecordType, number: Int) {
        Row(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painterResource(
                    id = when (elementType) {
                        RecordType.WATER_GLASSES -> R.drawable.ic_baseline_local_drink_48
                        RecordType.CALORIES -> R.drawable.ic_baseline_local_dining_48
                        RecordType.TRAINING_MINUTES -> R.drawable.ic_baseline_fire_48
                        else -> R.drawable.ic_baseline_add_24
                    },
                ),
                elementType.toString(),
                modifier = Modifier.size(48.dp),
                colorFilter = ColorFilter.tint(Color.Black)
            )
            Spacer(Modifier.size(64.dp))
            AddRemoveCard(number = number, elementType)
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