package com.example.sinteapp_3.ui.tudastar

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request.Method
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.sinteapp_3.NavigationSinteAppActivity

class TudastarViewModel(private val context: Context, private val dokuType: ArrayList<String>) : ViewModel() {

    private val _tudastar_tartalom = MutableLiveData<ArrayList<List<String>>>()
        init{
            tudastarTartalomListaz()
        }

    val tudastarLista: LiveData<ArrayList<List<String>>> get() = _tudastar_tartalom

    private fun tudastarTartalomListaz(){

        val ddd: ArrayList<List<String>>?= ArrayList()
        val tudastarInsertQueue=Volley.newRequestQueue(context)
        val dokuTipusok = dokuType
        for (dokuElemek in dokuTipusok) {
            val tudastarStringRequest=StringRequest(Method.GET, NavigationSinteAppActivity.getTudastarLink(dokuElemek),
                Response.Listener {response->
                    Log.d("tudastarLog", response)
                    val respTemp=response.split("\n")
                    val respTempList=respTemp.toList()
                    ddd?.add(respTempList)
                    _tudastar_tartalom.value = ddd!!
                },
                Response.ErrorListener {error->
                    Log.d("tudastarLogError", error.message.toString())

                })

            tudastarInsertQueue.add(tudastarStringRequest)
        }
    }
}

class TudastarViewModelFactory(private val context: Context, private val dokuType: ArrayList<String>) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TudastarViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TudastarViewModel(context, dokuType) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
