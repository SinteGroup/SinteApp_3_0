package com.example.sinteapp_3.ui.tudastar

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sinteapp_3.NavigationSinteAppActivity
import com.example.sinteapp_3.R
import com.example.sinteapp_3.foldingCellHelper
import com.rajat.pdfviewer.PdfViewerActivity
import com.rajat.pdfviewer.util.saveTo


class TudastarDokumentumFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root= inflater.inflate(R.layout.fragment_tudastar_dokumentum, container, false)

        tartalomGyartas(root, "pdf")

        PdfViewerActivity.launchPdfFromUrl(
            context = requireContext(),
            pdfUrl  = "http://192.168.73.203/tudastar/dokumentumok/Szkennelés_leírás.pdf",
            pdfTitle = "PDF Title",
            saveTo = saveTo.ASK_EVERYTIME,
            enableDownload = true
        )

        return root
    }

    private fun tartalomGyartas(root: View, mimeType: String){
        val context=requireContext()

        val tudastarViewModelFactory= TudastarViewModelFactory(context, arrayListOf(mimeType))

        val tudastarViewModel =
            ViewModelProvider(this, tudastarViewModelFactory).get(TudastarViewModel::class.java)

        val dataTemp= ArrayList<foldingCellHelper.TudastarDokumentumDataModel>()

        tudastarViewModel.tudastarLista.observe(viewLifecycleOwner, Observer<ArrayList<List<String>>>(){
            Log.d("TudastarFragment_size", it.size.toString())
            for (elements in it) {
                Log.d("TudastarFragment_lista_hatar", "----------------------")
                for(elem in elements){
                    Log.d("TudastarFragment_log_elements", NavigationSinteAppActivity.getTudastar_Dokumentum_mappa()+elem)
                    dataTemp.add(foldingCellHelper.TudastarDokumentumDataModel(elem))
                }
            }
            val recyclerView: RecyclerView = root.findViewById(R.id.tudastar_recycler_view)
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = foldingCellHelper.FoldingCellAdapter(dataTemp)
        })
    }
}

