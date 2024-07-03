package com.example.sinteapp_3.ui.gallery

import android.app.Application
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ViewSwitcher.ViewFactory
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.example.sinteapp_3.NavigationSinteAppActivity
import com.example.sinteapp_3.databinding.FragmentGalleryBinding
import java.sql.Connection

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val context=requireContext()

        val viewModelFactory=SinteViewModelFactory(context)

        val galleryViewModel =
            ViewModelProvider(this, viewModelFactory)[GalleryViewModel::class.java]

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var imageSlider = binding.imageSlider

        var playStatusBar = binding.playStopImageView

        galleryViewModel.imageList.observe(viewLifecycleOwner) {
            if(it.isNotEmpty()) {
                imageSlider.setImageList(it)
                imageSlider.setItemClickListener(object : ItemClickListener {
                    override fun doubleClick(position: Int) {
                        //Log.d("Click", "Double click")
                        imageSlider.startSliding(3000)
                        playStatusBar.setBackgroundColor(Color.GREEN)
                        //Toast.makeText(this@MainActivity, "Start", Toast.LENGTH_LONG).show()
                    }

                    override fun onItemSelected(position: Int) {
                        //Log.d("Click", "Simple click")
                        imageSlider.stopSliding()
                        playStatusBar.setBackgroundColor(Color.RED)
                        //Toast.makeText(this@MainActivity, "Stop", Toast.LENGTH_LONG).show()
                    }
                })
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class SinteViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GalleryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GalleryViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
