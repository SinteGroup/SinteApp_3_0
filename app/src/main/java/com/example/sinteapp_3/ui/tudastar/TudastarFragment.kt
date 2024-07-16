package com.example.sinteapp_3.ui.tudastar

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sinteapp_3.NavigationSinteAppActivity
import com.example.sinteapp_3.databinding.FragmentTudastarBinding
import com.google.android.material.tabs.TabLayoutMediator

class TudastarFragment : Fragment() {

    private var _binding: FragmentTudastarBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val context=requireContext()

        val tudastarViewModelFactory= TudastarViewModelFactory(context, arrayListOf("pdf", "videok"))

        val tudastarViewModel =
            ViewModelProvider(this, tudastarViewModelFactory).get(TudastarViewModel::class.java)

        _binding = FragmentTudastarBinding.inflate(inflater, container, false)
        val root: View = binding.root

        tudastarViewModel.tudastarLista.observe(viewLifecycleOwner, Observer<ArrayList<List<String>>>(){
            Log.d("TudastarFragment_size", it.size.toString())
            for (elements in it) {
                Log.d("TudastarFragment_lista_hatar", "----------------------")
                for(elem in elements){
                    Log.d("TudastarFragment_log_elements", NavigationSinteAppActivity.getTudastar_Dokumentum_mappa()+elem)
                }
            }
        })

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabNeve= listOf("Videók", "Dokumentumok")

        val adapter = ViewPagerAdapter(this)

        val viewPager=binding.viewPager
        viewPager.adapter = adapter

        val tabLayout=binding.tabLayout

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "${(tabNeve[position])}"
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}