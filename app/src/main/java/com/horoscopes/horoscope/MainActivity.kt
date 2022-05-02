package com.horoscopes.horoscope

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.horoscopes.horoscope.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible = true
            val response = try {
                RetrofitInstance.api.getForecast("aquarius","today")

            }
            catch (e: IOException){
                Log.e(TAG, "something wrong" )
                binding.progressBar.isVisible = false
                return@launchWhenCreated

            }
            catch (e: HttpException){
                Log.e(TAG, "something went wrong" )
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body() != null){

                binding.tvDescription.text = response.body()!!.description
                binding.tvMood.text = response.body()!!.mood
                binding.tvBestMatch.text = response.body()!!.compatibility
                binding.tvLuckyNumber.text = response.body()!!.lucky_number
                binding.tvLuckyTime.text = response.body()!!.lucky_time
                binding.tvRange.text = response.body()!!.date_range
                binding.tvSign.text = "hello aquarius,"
            }
            else{
                Log.e(TAG, "Response not successful", )
            }
            binding.progressBar.isVisible = false

            binding.icSearch.setOnClickListener(View.OnClickListener {
                binding.btnToday.isClickable = false
                lifecycleScope.launchWhenStarted{
                    binding.progressBar.isVisible = true
                    val response1 = try {
                        RetrofitInstance.api.getForecast(binding.edtSearch.text.toString(),"today")

                    }
                    catch (e: IOException){
                        Log.e(TAG, "something wrong" )
                        binding.progressBar.isVisible = false
                        return@launchWhenStarted
                    }
                    catch (e: HttpException){
                        Log.e(TAG, "something went wrong" )
                        binding.progressBar.isVisible = false
                        return@launchWhenStarted

                    }
                    if (response1.isSuccessful && response1.body() != null){

                        binding.tvDescription.text = response1.body()!!.description
                        binding.tvMood.text = response1.body()!!.mood
                        binding.tvBestMatch.text = response1.body()!!.compatibility
                        binding.tvLuckyNumber.text = response1.body()!!.lucky_number
                        binding.tvLuckyTime.text = response1.body()!!.lucky_time
                        binding.tvRange.text = response1.body()!!.date_range
                        binding.tvSign.text = "hello "+binding.edtSearch.text.toString()+","
                    }
                    else{
                        Log.e(TAG, "Response not successful", )
                    }
                    binding.progressBar.isVisible = false

                }

            })
            binding.btnTomorrow.setOnClickListener(View.OnClickListener {
                binding.btnToday.isClickable = true
                lifecycleScope.launchWhenStarted{
                    binding.progressBar.isVisible = true
                    val response1 = try {
                        RetrofitInstance.api.getForecast(binding.edtSearch.text.toString(),"tomorrow")

                    }
                    catch (e: IOException){
                        Log.e(TAG, "something wrong" )
                        binding.progressBar.isVisible = false
                        return@launchWhenStarted
                    }
                    catch (e: HttpException){
                        Log.e(TAG, "something went wrong" )
                        binding.progressBar.isVisible = false
                        return@launchWhenStarted

                    }
                    if (response1.isSuccessful && response1.body() != null){

                        binding.tvDescription.text = response1.body()!!.description
                        binding.tvMood.text = response1.body()!!.mood
                        binding.tvBestMatch.text = response1.body()!!.compatibility
                        binding.tvLuckyNumber.text = response1.body()!!.lucky_number
                        binding.tvLuckyTime.text = response1.body()!!.lucky_time
                        binding.tvRange.text = response1.body()!!.date_range
                        binding.tvSign.text = "hello "+binding.edtSearch.text.toString()+","
                    }
                    else{
                        Log.e(TAG, "Response not successful", )
                    }
                    binding.progressBar.isVisible = false

                }


            })
            binding.btnToday.setOnClickListener(View.OnClickListener {
                lifecycleScope.launchWhenStarted{
                    binding.progressBar.isVisible = true
                    val response1 = try {
                        RetrofitInstance.api.getForecast(binding.edtSearch.text.toString(),"today")

                    }
                    catch (e: IOException){
                        Log.e(TAG, "something wrong" )
                        binding.progressBar.isVisible = false
                        return@launchWhenStarted
                    }
                    catch (e: HttpException){
                        Log.e(TAG, "something went wrong" )
                        binding.progressBar.isVisible = false
                        return@launchWhenStarted

                    }
                    if (response1.isSuccessful && response1.body() != null){

                        binding.tvDescription.text = response1.body()!!.description
                        binding.tvMood.text = response1.body()!!.mood
                        binding.tvBestMatch.text = response1.body()!!.compatibility
                        binding.tvLuckyNumber.text = response1.body()!!.lucky_number
                        binding.tvLuckyTime.text = response1.body()!!.lucky_time
                        binding.tvRange.text = response1.body()!!.date_range
                        binding.tvSign.text = "hello "+binding.edtSearch.text.toString()+","
                    }
                    else{
                        Log.e(TAG, "Response not successful", )
                    }
                    binding.progressBar.isVisible = false

                }

            })

        }

    }

}