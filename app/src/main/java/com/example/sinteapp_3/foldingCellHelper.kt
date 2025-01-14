package com.example.sinteapp_3

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ramotion.foldingcell.FoldingCell

class foldingCellHelper {

    data class TudastarDokumentumDataModel(
        val newText:String,
        val view: View
    )
    class FoldingCellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val newCellTitleTextView: TextView = itemView.findViewById(R.id.tudastar_dokumentumok_cell_title_view_TextView)

    }
    class FoldingCellAdapter(private val dataList: List<TudastarDokumentumDataModel>) : RecyclerView.Adapter<FoldingCellViewHolder>() {

        @SuppressLint("SetJavaScriptEnabled")
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoldingCellViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.tudastar_dokumentumok_foldingcel_item_layout, parent, false)

            val fc: FoldingCell = view.findViewById(R.id.dokumentumok_folding_cell)

            fc.setOnClickListener {
                fc.toggle(false)
            }

            /*val webView: WebView = view.findViewById(R.id.tudastar_dokumentumok_cell_title_view_WebView)
            webView.settings.javaScriptEnabled = true
            webView.settings.allowFileAccess = true
            val urlString="http://195.228.220.2:65535/tudastar/dokumentumok/"+dataList[0].newText
            webView.loadUrl("https://docs.google.com/gview?embedded=true&url="+Uri.parse(urlString))*/

            for(elem in dataList){
                Log.d("dataTok", elem.toString())
            }


            return FoldingCellViewHolder(view)
        }

        override fun onBindViewHolder(holder: FoldingCellViewHolder, position: Int) {
            val data = dataList[position]
            holder.newCellTitleTextView.text = data.newText
            //val webView: WebView = data.view.findViewById(R.id.tudastar_dokumentumok_cell_title_view_WebView)
            /*val urlString="http://195.228.220.2:65535/tudastar/dokumentumok/"+dataList[position].newText
            webView.loadUrl("https://docs.google.com/gview?embedded=true&url="+ Uri.parse(urlString))*/
        }

        override fun getItemCount(): Int {
            return dataList.size
        }
    }


}