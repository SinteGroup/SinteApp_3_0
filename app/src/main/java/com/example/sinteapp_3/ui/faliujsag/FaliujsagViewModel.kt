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
import com.example.sinteapp_3.NetworkStatusHelper
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


class FaliujsagViewModel(private val context: Context) : ViewModel() {

    val imageListViewModel= ArrayList<SlideModel>()
    private var _imageList = MutableLiveData<ArrayList<SlideModel>>()
    val imageList: LiveData<ArrayList<SlideModel>> = _imageList

    private val scheduler = Executors.newScheduledThreadPool(1)

    private val networkStatusHelper = NetworkStatusHelper(context)

    init {
        networkStatusHelper.isConnected.observeForever{isConnected ->
            if(isConnected){
                refreshData(5, TimeUnit.MINUTES)
            }
        }
    }

    private fun hirdetesek(context: Context){

        var returnLista: ArrayList<SlideModel> = ArrayList<SlideModel>()
        var fileokLista=""
        val scripturl = NavigationSinteAppActivity.getFaliujsagScriptFiles()
        val faliujsagLink = NavigationSinteAppActivity.getFaliujsagMappa()

        val insertDataQueqe = Volley.newRequestQueue(context)

        val insertDataStringRequest= StringRequest(
            Request.Method.GET, scripturl,
            { response ->
                Log.d("Volley_response", response)
                fileokLista = response
                val fileTempCsakhogyLegyen=fileokLista.split("\n")

                for (fileBelsoTempCsakhogyBonyiLegyen in fileTempCsakhogyLegyen) {
                    Log.d("Szerver_Belso",faliujsagLink+fileBelsoTempCsakhogyBonyiLegyen)
                    returnLista.add(
                        SlideModel(
                            faliujsagLink+fileBelsoTempCsakhogyBonyiLegyen,
                            "Nem kell title"
                        )
                    )
                }
                _imageList.value=returnLista
            },
            {
                    error->
                Log.d("Volley_error", error.message.toString())
            })
        insertDataQueqe.add(insertDataStringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        scheduler.shutdown()
    }

    fun refreshData(periodus: Long, lepes: TimeUnit){
        scheduler.scheduleAtFixedRate({
            hirdetesek(context)
        }, 0, periodus,lepes) // 0 kezdeti késleltetés, 1 perc intervallum
    }
}

