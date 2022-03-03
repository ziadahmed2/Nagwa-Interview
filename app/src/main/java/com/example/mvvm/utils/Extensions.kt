package com.example.mvvm.utils

import android.content.res.Resources.getSystem
import android.widget.Toast.LENGTH_SHORT
import android.widget.Toast.makeText
import com.example.mvvm.BaseMvvmApplication

val Int.toPx: Int get() = (this * getSystem().displayMetrics.density).toInt()

fun showMessage(msg: String) = makeText(BaseMvvmApplication.appContext,msg, LENGTH_SHORT).show()