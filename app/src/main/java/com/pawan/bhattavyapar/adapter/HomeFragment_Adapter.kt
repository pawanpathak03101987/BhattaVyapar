package com.pawan.bhattavyapar.adapter

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.FragmentActivity
import com.pawan.bhattavyapar.R
import com.pawan.bhattavyapar.ui.fragments.CustomerProfileFragment
import com.pawan.bhattavyapar.ui.fragments.RecivingBalanceFragment
import kotlinx.android.synthetic.main.home_adapter.view.*
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

            view.lyConMain.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("fragmentName", "Customer Profile")
                val moviesFragment = CustomerProfileFragment()
                moviesFragment.arguments = bundle
                activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.activity_main_content_id, moviesFragment).commit()
            }
        }
    }}
}