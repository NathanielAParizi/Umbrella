package com.example.umbrella

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.umbrella.Datasource.Remote.OkHttpHelper
import com.example.umbrella.Model.Weather
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

const val URL = "api.openweathermap.org/data/2.5/weather?zip="
const val APIKEY = ",us&appid=419a8e49d791fce2b59ef848c362df9c"

class MainActivity : AppCompatActivity() {

    var fullURL = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onUserResponse(userResponse: Weather) {


        txtLocal.text = "Base: ${userResponse.base} " +
                " Description: ${userResponse.weather[0].description} " +
                " Clouds:${userResponse.clouds}" +
                "Coordinates:${userResponse.coord}  " +
                " City:${userResponse.name}"
        Log.d("TAG", "weather API call is working")


    }

    fun executeAsyncOkHttpCall() {



//        val url = "https://api.github.com/search/users?q=nathanielaparizi"
//        val okHttpHelper = OkHttpHelper()
//        okHttpHelper.makeAsyncApiCall(url)

        var zipcode = editText.text.toString()
        fullURL = "http://api.openweathermap.org/data/2.5/weather?zip=29229,us&appid=419a8e49d791fce2b59ef848c362df9c"
        val okHttpHelper = OkHttpHelper()
        okHttpHelper.makeAsyncApiCall(fullURL)
    }


    fun onClick(view: View) {

        //  fullURL = "http://api.openweathermap.org/data/2.5/weather?zip=29229,us&appid=419a8e49d791fce2b59ef848c362df9c"
        Log.d("TAG", "this is the JSON String: " + fullURL)
        executeAsyncOkHttpCall()
    }
}
