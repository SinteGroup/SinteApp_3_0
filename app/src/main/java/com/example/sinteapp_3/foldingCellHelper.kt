package com.example.sinteapp_3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
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
        val tudastar_pdfWebView: WebView = itemView.findViewById(R.id.tudastar_pdfWebView)
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

            val pdfUrl = "http://195.228.220.2:65535/tudastar/dokumentumok/Szkennelés_leírás.pdf"
            holder.tudastar_pdfWebView.loadUrl("https://docs.google.com/gview?embedded=true&url=$pdfUrl")
        }

        override fun getItemCount(): Int {
            return dataList.size
        }
    }


}