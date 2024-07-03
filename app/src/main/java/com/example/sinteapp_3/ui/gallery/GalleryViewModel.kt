package com.example.sinteapp_3.ui.gallery

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.CountDownTimer
import android.transition.Slide
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.denzcoskun.imageslider.models.SlideModel


class GalleryViewModel(private val context: Context) : ViewModel() {

    val imageListViewModel= ArrayList<SlideModel>()
    private var _imageList = MutableLiveData<ArrayList<SlideModel>>().apply {

        value = hirdetesek("script/getFiles.php", "http://195.228.220.2:65535/", context)

    }
    val imageList: LiveData<ArrayList<SlideModel>> = _imageList

    private fun hirdetesek(url: String, domain: String, context: Context):ArrayList<SlideModel>{

        var returnLista: ArrayList<SlideModel> = ArrayList<SlideModel>()
        var fileokLista=""
        val domain=domain
        val url =domain+url

        val insertDataQueqe = Volley.newRequestQueue(context)

        val insertDataStringRequest= StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                Log.d("Volley_response", response)
                fileokLista = response
                val fileTempCsakhogyLegyen=fileokLista.split("\n")

                for (fileBelsoTempCsakhogyBonyiLegyen in fileTempCsakhogyLegyen) {
                    Log.d("Szerver_Belso", domain + "kepek/$fileBelsoTempCsakhogyBonyiLegyen")
                    returnLista.add(
                        SlideModel(
                            domain+"kepek/$fileBelsoTempCsakhogyBonyiLegyen",
                            "Nem kell title"
                        )
                    )
                }
            },
            Response.ErrorListener {
                    error->
                Log.d("Volley_error", error.message.toString())
            })
        //insertDataQueqe.add(insertDataStringRequest)

        val timer = object: CountDownTimer(1800, 1800) {
            override fun onTick(millisUntilFinished: Long) {
                Log.d("Timer____", millisUntilFinished.toString())
                insertDataQueqe.add(insertDataStringRequest)
            }
            override fun onFinish() {
                Log.d("Timer_Finish", "Finish_onStart")
                super.start()
            }
        }
        timer.start()

        return returnLista
    }

}

