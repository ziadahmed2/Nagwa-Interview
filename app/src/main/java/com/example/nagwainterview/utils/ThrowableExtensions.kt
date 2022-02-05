package com.example.nagwainterview.utils

import android.content.Context
import android.util.Log
import com.example.nagwainterview.NagwaApplication
import com.example.nagwainterview.R
import retrofit2.HttpException

fun Throwable.handle() {
    if(this is HttpException){
        when(code()) {
            401 -> NagwaApplication.appContext?.logout()
            500 -> showMessage(NagwaApplication.appContext?.getString(R.string.connection_error_msg)!!)
            else -> showMessage("$message")
        }
    }
    else showMessage("$message")
}

private fun Context.logout() {
    Log.d("", "logout: App should logout")
}