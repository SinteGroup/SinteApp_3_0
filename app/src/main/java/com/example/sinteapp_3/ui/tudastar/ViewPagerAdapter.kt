package com.example.sinteapp_3.ui.tudastar

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2 // Két fül
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TudastarDokumentumFragment()
            else -> TudastarVideoFragment()
        }
    }

}
