package com.example.sinteapp_3.ui.gallery

import android.content.Context
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
import com.example.sinteapp_3.NavigationSinteAppActivity

class GalleryViewModel : ViewModel() {

    val imageListViewModel= ArrayList<SlideModel>()
    private val _imageList = MutableLiveData<ArrayList<SlideModel>>().apply {

        value = hirdetesek("script/getFiles.php", "http://195.228.220.2:65535")

    }
    val imageList: LiveData<ArrayList<SlideModel>> = _imageList
private fun hirdetesek(url: String, domain: String):ArrayList<SlideModel>{

    var returnLista: ArrayList<SlideModel> = ArrayList()
    var fileokLista=""
    val domain=domain
    val url =url
    /*var context GalleryFragment
    val insertDataQueqe = Volley.newRequestQueue()*/

    val insertDataStringRequest=StringRequest(Request.Method.GET, url,
        Response.Listener<String> { response ->Log.d("fdsafds", response)
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
            error->Log.d("Volley_error", error.message.toString())
        })
    //insertDataQueqe.add(insertDataStringRequest)

    return returnLista
}
}

