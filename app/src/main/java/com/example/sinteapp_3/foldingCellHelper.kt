package com.example.sinteapp_3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ramotion.foldingcell.FoldingCell

class foldingCellHelper {

    data class TudastarDokumentumDataModel(
        val newText: String
        // Egyéb adatok
    )
    class FoldingCellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val newCellTitleTextView: TextView = itemView.findViewById(R.id.tudastar_dokumentumok_cell_title_view_TextView)
    }
    class FoldingCellAdapter(private val dataList: List<TudastarDokumentumDataModel>) : RecyclerView.Adapter<FoldingCellViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoldingCellViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.tudastar_dokumentumok_foldingcel_item_layout, parent, false)

            val fc: FoldingCell = view.findViewById(R.id.dokumentumok_folding_cell)

            fc.setOnClickListener {
                fc.toggle(false)
            }

            return FoldingCellViewHolder(view)
        }

        override fun onBindViewHolder(holder: FoldingCellViewHolder, position: Int) {
            val data = dataList[position]
            holder.newCellTitleTextView.text = data.newText
            // Állítsd be a többi elemet is
        }

        override fun getItemCount(): Int {
            return dataList.size
        }
    }


}