package com.horoscopes.horoscope.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.horoscopes.horoscope.data.models.Forecast
import com.horoscopes.horoscope.util.DispatcherProvider
import com.horoscopes.horoscope.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository,
    private val dispatchers: DispatcherProvider
): ViewModel() {
    sealed class PredictionEvent {
        class Success(val resultText: Forecast?): PredictionEvent()
        class Failure(val errorText: String): PredictionEvent()
        object Loading : PredictionEvent()
        object Empty : PredictionEvent()
    }

    private val _prediction = MutableStateFlow<PredictionEvent>(PredictionEvent.Empty)
    val prediction: StateFlow<PredictionEvent> = _prediction

    fun predict(
        sign:String,
        day: String
    ){
        viewModelScope.launch(dispatchers.io) {
            _prediction.value = PredictionEvent.Loading
            when( val predResponse = repository.getPredictions(sign, day)){
                is Resource.Error -> _prediction.value = PredictionEvent.Failure(predResponse.message!!)
                is Resource.Success ->{
                    val response = predResponse.data
                    if (response == null){
                        _prediction.value = PredictionEvent.Failure("Unexpected Error")
                    } else{
                        _prediction.value =PredictionEvent.Success(
                            response
                        )
                    }
                }
            }
        }
    }
}