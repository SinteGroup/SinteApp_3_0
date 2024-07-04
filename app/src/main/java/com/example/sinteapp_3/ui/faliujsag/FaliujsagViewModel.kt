package com.example.sinteapp_3.ui.faliujsag

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.denzcoskun.imageslider.models.SlideModel
import com.example.sinteapp_3.NavigationSinteAppActivity
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


class FaliujsagViewModel(private val context: Context) : ViewModel() {

    val imageListViewModel= ArrayList<SlideModel>()
    private var _imageList = MutableLiveData<ArrayList<SlideModel>>()
    val imageList: LiveData<ArrayList<SlideModel>> = _imageList

    private val scheduler = Executors.newScheduledThreadPool(1)

    init {
        startRepeatingTask()
    }

    private fun hirdetesek(scripturl: String, domain: String, context: Context){

        var returnLista: ArrayList<SlideModel> = ArrayList<SlideModel>()
        var fileokLista=""
        val domain=NavigationSinteAppActivity.getDomain()
        val scripturl =domain+scripturl

        val insertDataQueqe = Volley.newRequestQueue(context)

        val insertDataStringRequest= StringRequest(
            Request.Method.GET, scripturl,
            Response.Listener<String> { response ->
                Log.d("Volley_response", response)
                fileokLista = response
                val fileTempCsakhogyLegyen=fileokLista.split("\n")

                for (fileBelsoTempCsakhogyBonyiLegyen in fileTempCsakhogyLegyen) {
                    Log.d("Szerver_Belso", domain + "faliujsag/$fileBelsoTempCsakhogyBonyiLegyen")
                    returnLista.add(
                        SlideModel(
                            domain+"faliujsag/$fileBelsoTempCsakhogyBonyiLegyen",
                            "Nem kell title"
                        )
                    )
                }
                _imageList.value=returnLista
            },
            Response.ErrorListener {
                    error->
                Log.d("Volley_error", error.message.toString())
            })
        insertDataQueqe.add(insertDataStringRequest)
    }

    private fun startRepeatingTask() {
        scheduler.scheduleAtFixedRate({
            hirdetesek(NavigationSinteAppActivity.getScriptFiles(), NavigationSinteAppActivity.getDomain(), context)
        }, 0, 5, TimeUnit.MINUTES) // 0 kezdeti késleltetés, 1 perc intervallum
    }

    override fun onCleared() {
        super.onCleared()
        scheduler.shutdown()
    }

}

