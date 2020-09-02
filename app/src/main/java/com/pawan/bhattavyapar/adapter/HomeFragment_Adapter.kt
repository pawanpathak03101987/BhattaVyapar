package com.pawan.bhattavyapar.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.FragmentActivity
import com.pawan.bhattavyapar.R
import java.util.ArrayList

class HomeFragment_Adapter {

    companion object{
    public fun createList(activity: FragmentActivity,lyMain:LinearLayout)
    {
        val inflater: LayoutInflater =
            activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        lyMain.removeAllViews()

        for (i in 0 until 5) {
            val view: ViewGroup =
                inflater.inflate(R.layout.home_adapter, lyMain, false) as ViewGroup
            lyMain.addView(view)
        }
    }}
}