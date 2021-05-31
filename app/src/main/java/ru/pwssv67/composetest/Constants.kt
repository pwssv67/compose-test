package ru.pwssv67.composetest

enum class RecordType(val typeString:String) {
    WATER_GLASSES("WATER_GLASSES"),
    CALORIES("CALORIES"),
    TRAINING_MINUTES("TRAINING_MINUTES")
}

val defaultLimits = mapOf(
    RecordType.WATER_GLASSES to 8,
    RecordType.CALORIES to 2400,
    RecordType.TRAINING_MINUTES to 30
)