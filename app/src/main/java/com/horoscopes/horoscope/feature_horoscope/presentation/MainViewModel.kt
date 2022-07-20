package com.horoscopes.horoscope.feature_horoscope.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.horoscopes.horoscope.feature_horoscope.domain.model.Forecast
import com.horoscopes.horoscope.feature_horoscope.domain.use_case.GetPrediction
import com.horoscopes.horoscope.util.DispatcherProvider
import com.horoscopes.horoscope.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPrediction: GetPrediction,
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
            getPrediction(sign, day).collect { result ->
                when(result){
                    is Resource.Success ->
                    {
                        val response = result.data
                        if (response == null){
                            _prediction.value = PredictionEvent.Failure("Unexpected Error")
                        } else{
                            _prediction.value = PredictionEvent.Success(response)
                        }
                    }
                    is Resource.Loading ->
                    {
                        _prediction.value = PredictionEvent.Loading
                    }
                    is Resource.Error -> _prediction.value = PredictionEvent.Failure(result.message.toString())
                }
            }

        }
    }
}