package ru.pwssv67.composetest

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainLayout()
        }
    }

    @Preview(
        showBackground = true,
        showSystemUi = true, backgroundColor = 0xFFFFFFFF, device = Devices.NEXUS_5
    )
    @Composable
    private fun MainLayout() {
        MaterialTheme() {
            Surface(color = MaterialTheme.colors.background) {
                BoxWithConstraints() {
                    ConstraintLayout(decoupledConstraints(16.dp)) {

                        ColumnWithText(
                            modifier = Modifier.layoutId("text")
                        )

                        Button(
                            onClick = {},
                            modifier = Modifier.layoutId("button"),
                        ) {
                            Text("Button")
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun ColumnWithText(texts:List<Pair<String, Int>> = listOf("Do Something With Your Life" to 18, "kek" to 18), modifier: Modifier = Modifier) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    texts.map {
                        Text(it.first, fontSize = it.second.sp)
                    }
                }
            }

    private fun decoupledConstraints(margin: Dp): ConstraintSet {
        return ConstraintSet {
            val button = createRefFor("button")
            val text = createRefFor("text")

            val chainStyle = ChainStyle.Packed(0.5f)
            createVerticalChain(text, button, chainStyle = chainStyle)

            constrain(text) {
                top.linkTo(parent.top, margin = margin)
                bottom.linkTo(button.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            constrain(button) {
                top.linkTo(text.bottom)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }

        }
    }

}