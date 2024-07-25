package com.example.sinteapp_3.ui.tudastar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sinteapp_3.R
import com.example.sinteapp_3.foldingCellHelper


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

        val dataList = listOf(
            foldingCellHelper.TudastarDokumentumDataModel("Első elem szövege"),
            foldingCellHelper.TudastarDokumentumDataModel("Második elem szövege"),
            foldingCellHelper.TudastarDokumentumDataModel("Harmadik elem szövege"))

        val recyclerView: RecyclerView = root.findViewById(R.id.tudastar_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = foldingCellHelper.FoldingCellAdapter(dataList)

        return root
    }
}

