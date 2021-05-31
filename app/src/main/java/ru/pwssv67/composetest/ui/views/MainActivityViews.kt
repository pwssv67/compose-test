package ru.pwssv67.composetest.ui.views

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.pwssv67.composetest.R
import ru.pwssv67.composetest.RecordType

val defaultLimits = mapOf(
    RecordType.WATER_GLASSES to 8,
    RecordType.CALORIES to 2400,
    RecordType.TRAINING_MINUTES to 30
)

@Composable
fun AddRemoveCard(
    number: Int,
    elementType: RecordType,
    limits:Map<RecordType, Int>,
    addWithType: (RecordType)->Unit = {},
    decreaseWithType:(RecordType)->Unit ={}
) {
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
        transitionSpec = { spring(1.5f) }
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
            Image(
                painterResource(id = R.drawable.ic_baseline_remove_24), "remove",
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
            Image(
                painterResource(id = R.drawable.ic_baseline_add_24), "add",
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
fun ElementCard(
    elementType: RecordType,
    number: Int,
    limits: Map<RecordType, Int>,
    increseCallback:(RecordType)->Unit = {},
    decreaseCallback:(RecordType)->Unit = {}
) {
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
        AddRemoveCard(number = number, elementType, limits, increseCallback, decreaseCallback)
    }
}

@Preview
@Composable
private fun PreviewCaloriesViewCard() {
    Surface(Modifier.background(MaterialTheme.colors.background)) {
        ElementCard(elementType = RecordType.WATER_GLASSES, 40, defaultLimits)
    }
}