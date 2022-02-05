package com.example.nagwainterview.viewmodel.main

import android.util.Log
import android.widget.Toast
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
                    val list: ArrayList<FileModel> = arrayListOf()
                    list.add(FileModel(1,"VIDEO","https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4","Video 1"))
                    list.add(FileModel(2,"VIDEO","https://bestvpn.org/html5demos/assets/dizzy.mp4","Video 2"))
                    list.add(FileModel(3,"PDF","https://kotlinlang.org/docs/kotlin-reference.pdf","PDF 3"))
                    list.add(FileModel(4,"VIDEO","https://storage.googleapis.com/exoplayer-test-media-1/mp4/frame-counter-one-hour.mp4","Video 4"))
                    list.add(FileModel(5,"PDF","https://www.cs.cmu.edu/afs/cs.cmu.edu/user/gchen/www/download/java/LearnJava.pdf","PDF 5"))
                    list.add(FileModel(6,"VIDEO","https://storage.googleapis.com/exoplayer-test-media-1/mp4/android-screens-10s.mp4","Video 6"))
                    list.add(FileModel(7,"VIDEO","https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4","Video 7"))
                    list.add(FileModel(8,"VIDEO","https://storage.googleapis.com/exoplayer-test-media-1/mp4/android-screens-25s.mp4","Video 8"))
                    list.add(FileModel(9,"PDF","https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf","PDF 9"))
                    list.add(FileModel(10,"PDF","https://en.unesco.org/inclusivepolicylab/sites/default/files/dummy-pdf_2.pdf","PDF 10"))
                    list.add(FileModel(11,"VIDEO","https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4","Video 11"))
                    list.add(FileModel(12,"VIDEO","https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4","Video 12"))
                    liveDataGetListOfFiles.value = list
                    Log.e(TAG, "getListOfFiles: $throwable")
                    handleThrowable(throwable)
                })
    }
}