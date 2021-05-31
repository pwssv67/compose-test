package ru.pwssv67.composetest.ui.views

//@Composable
//fun ElementCard(elementType: RecordType, number: Int) {
//    Row(
//        modifier =
//        Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 16.dp, vertical = 12.dp),
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.SpaceBetween
//    ) {
//        Image(
//            painterResource(
//                id = when (elementType) {
//                    RecordType.WATER_GLASSES -> R.drawable.ic_baseline_local_drink_48
//                    RecordType.CALORIES -> R.drawable.ic_baseline_local_dining_48
//                    RecordType.TRAINING_MINUTES -> R.drawable.ic_baseline_fire_48
//                    else -> R.drawable.ic_baseline_add_24
//                },
//            ),
//            elementType.toString(),
//            modifier = Modifier.size(48.dp),
//            colorFilter = ColorFilter.tint(Color.Black)
//        )
//        Spacer(Modifier.size(64.dp))
//        AddRemoveCard(number = number, elementType)
//    }
//}