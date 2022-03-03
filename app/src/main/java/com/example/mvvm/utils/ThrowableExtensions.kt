package com.example.mvvm.utils

import android.content.Context
import android.util.Log
import com.example.mvvm.BaseMvvmApplication
import com.example.mvvm.R
import retrofit2.HttpException

fun Throwable.handle() {
    if(this is HttpException){
        when(code()) {
            401 -> BaseMvvmApplication.appContext?.logout()
            500 -> showMessage(BaseMvvmApplication.appContext?.getString(R.string.connection_error_msg)!!)
            else -> showMessage("$message")
        }
    }
    else showMessage("$message")
}

private fun Context.logout() {
    Log.d("", "logout: App should logout")
}