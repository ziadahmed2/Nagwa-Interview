package com.example.nagwainterview.viewmodel.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.nagwainterview.base.BaseViewModel
import com.example.nagwainterview.di.main.MainScope
import com.example.nagwainterview.domain.main.MainRepo
import com.example.nagwainterview.model.FileModel
import com.example.nagwainterview.utils.observeOnMainThread
import com.example.nagwainterview.utils.subscribeOnIO
import javax.inject.Inject

@MainScope
class MainViewModel @Inject constructor(): BaseViewModel(){

    companion object{
        private val TAG = MainViewModel::class.java.simpleName
    }

    @Inject
    lateinit var repo: MainRepo

    var liveDataGetListOfFiles: MutableLiveData<ArrayList<FileModel>?> = MutableLiveData()
    fun getListOfFiles(){
        showProgress()
        repo.getListOfFiles()
                ?.subscribeOnIO
                ?.observeOnMainThread
                ?.subscribe({ model ->
                    hideProgress()
                    liveDataGetListOfFiles.value = model
                    Log.d(TAG, "getListOfFiles: ${model?.get(0)?.name}")
                }, { throwable ->
                    hideProgress()
                    Log.e(TAG, "getListOfFiles: $throwable")
                })
    }
}