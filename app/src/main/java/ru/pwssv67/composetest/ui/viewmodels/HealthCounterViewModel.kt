package ru.pwssv67.composetest.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.pwssv67.composetest.RecordType

class HealthCounterViewModel(application: Application):AndroidViewModel(application) {

    val glasses = MutableLiveData(7)
    val calories = MutableLiveData(2400)
    val training = MutableLiveData(25)

    val limits = mapOf(
        RecordType.WATER_GLASSES to 8,
        RecordType.CALORIES to 2400,
        RecordType.TRAINING_MINUTES to 30
    )

    fun addWithType(elementType: RecordType) {
        when (elementType) {
            RecordType.WATER_GLASSES -> glasses.postValue(glasses.value?.plus(1))
            RecordType.CALORIES -> calories.postValue(calories.value?.plus(1))
            RecordType.TRAINING_MINUTES -> training.postValue(training.value?.plus(1))
            else -> glasses.postValue(glasses.value?.plus(1))
        }
    }

    fun decreaseWithType(elementType: RecordType) {
        when (elementType) {
            RecordType.WATER_GLASSES -> glasses.postValue(glasses.value?.minus(1))
            RecordType.CALORIES -> calories.postValue(calories.value?.minus(1))
            RecordType.TRAINING_MINUTES -> training.postValue(training.value?.minus(1))
            else -> glasses.postValue(glasses.value?.minus(1))
        }
    }

}