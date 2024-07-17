package com.example.sinteapp_3.ui.tudastar

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sinteapp_3.R
import com.ramotion.foldingcell.FoldingCell

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

        val fc: FoldingCell = root.findViewById(R.id.folding_cell)

        /*fc.initialize(1000, Color.DKGRAY, 2);

        fc.initialize(30, 1000, Color.DKGRAY, 2);*/

        fc.setOnClickListener {
            fc.toggle(false)
        }
        return root
    }
}