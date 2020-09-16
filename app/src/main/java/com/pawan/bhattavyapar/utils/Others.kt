package com.pawan.bhattavyapar.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class Others {

    companion object{
        fun getCurrentDate(): String? {
            val df: DateFormat = SimpleDateFormat("yyyy-MM-dd")
            val dateobj = Date()
            return df.format(dateobj)
        }
    }
}