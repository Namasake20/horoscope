package com.horoscopes.horoscope

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.horoscopes.horoscope.databinding.ActivityMainBinding
import com.horoscopes.horoscope.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.icSearch.setOnClickListener {
            viewModel.predict(binding.edtSearch.text.toString(),"today")
            appendTitles()
        }
        binding.btnToday.setOnClickListener {
            viewModel.predict(binding.edtSearch.text.toString(),"today")
            appendTitles()
        }
        binding.btnTomorrow.setOnClickListener {
            viewModel.predict(binding.edtSearch.text.toString(),"tomorrow")
            appendTitles()
        }
        
        lifecycleScope.launchWhenCreated {
            viewModel.predict("aquarius","today")
            viewModel.prediction.collect { event ->
                when (event) {
                    is MainViewModel.PredictionEvent.Success -> {
                        binding.progressBar.isVisible = false
                        binding.tvDescription.text = event.resultText!!.description
                        binding.tvMood.text = event.resultText.mood
                        binding.tvLuckyTime.text = event.resultText.lucky_time
                        binding.tvLuckyNumber.text = event.resultText.lucky_number
                        binding.tvRange.text = event.resultText.date_range
                        binding.tvBestMatch.text = event.resultText.compatibility
                        binding.tvSign.text = "Hello, Aquarius!"
                    }
                    is MainViewModel.PredictionEvent.Failure ->{
                        binding.progressBar.isVisible = false
                        Log.e(TAG, "something wrong" )
                    }
                    is MainViewModel.PredictionEvent.Loading ->{
                        binding.progressBar.isVisible = true
                    }
                    is MainViewModel.PredictionEvent.Empty -> Unit
                }
            }

        }
    }

    private fun appendTitles() {
        lifecycleScope.launchWhenStarted {
            viewModel.prediction.collect { event ->
                when (event) {
                    is MainViewModel.PredictionEvent.Success -> {
                        binding.progressBar.isVisible = false
                        binding.tvDescription.text = event.resultText!!.description
                        binding.tvMood.text = event.resultText.mood
                        binding.tvLuckyTime.text = event.resultText.lucky_time
                        binding.tvLuckyNumber.text = event.resultText.lucky_number
                        binding.tvRange.text = event.resultText.date_range
                        binding.tvBestMatch.text = event.resultText.compatibility
                        binding.tvSign.text = "Hello, "+binding.edtSearch.text.toString()+"!"
                    }
                    is MainViewModel.PredictionEvent.Failure ->{
                        binding.progressBar.isVisible = false
                        Log.e(TAG, "something wrong" )
                    }
                    is MainViewModel.PredictionEvent.Loading ->{
                        binding.progressBar.isVisible = true
                    }
                    is MainViewModel.PredictionEvent.Empty -> Unit
                }
            }
        }
    }

}