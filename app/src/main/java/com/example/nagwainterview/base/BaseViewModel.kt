package com.example.nagwainterview.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nagwainterview.utils.showMessage

open class BaseViewModel : ViewModel() {
    val liveDataProgressBar = MutableLiveData<Boolean>()

    fun showProgress(){
        liveDataProgressBar.value = true
    }

    fun hideProgress(){
        liveDataProgressBar.value = false
    }

    fun handleError(error: String?){
        hideProgress()
        if(!error.isNullOrEmpty()){
            showMessage(error)
        }
    }
}