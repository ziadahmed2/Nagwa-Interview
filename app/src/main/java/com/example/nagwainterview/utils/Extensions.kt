package com.example.nagwainterview.utils

import android.content.res.Resources.getSystem
import android.widget.Toast.LENGTH_SHORT
import android.widget.Toast.makeText
import com.example.nagwainterview.NagwaApplication

val Int.toPx: Int get() = (this * getSystem().displayMetrics.density).toInt()

fun showMessage(msg: String) = makeText(NagwaApplication.appContext,msg, LENGTH_SHORT).show()