package com.example.mvvm.viewmodel.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mvvm.BaseMvvmApplication
import com.example.mvvm.R
import com.example.mvvm.base.BaseViewModel
import com.example.mvvm.di.home.HomeScope
import com.example.mvvm.domain.home.HomeRepo
import com.example.mvvm.model.FileModel
import com.example.mvvm.utils.FileDownloader
import com.example.mvvm.utils.observeOnMainThread
import com.example.mvvm.utils.showMessage
import com.example.mvvm.utils.subscribeOnIO
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HomeScope
class HomeViewModel @Inject constructor(): BaseViewModel(){

    companion object{
        private val TAG = HomeViewModel::class.java.simpleName
    }

    @Inject
    lateinit var repo: HomeRepo
    private val fileDownloader by lazy {
        FileDownloader(
            OkHttpClient.Builder().build()
        )
    }


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
                    returnMockDataForTestingPurposes()
                    Log.e(TAG, "getListOfFiles: $throwable")
                    handleThrowable(throwable)
                })
    }

    var liveDataDownloadFile: MutableLiveData<Boolean> = MutableLiveData()
    fun downloadFile(url: String?, targetFile: File?){
        showProgress()
        fileDownloader.download(url!!, targetFile!!)
            .throttleFirst(2, TimeUnit.SECONDS)
            .toFlowable(BackpressureStrategy.LATEST)
            .subscribeOn(Schedulers.io())
            .observeOn(mainThread())
            .subscribe({
                showMessage(BaseMvvmApplication.appContext?.getString(R.string.download_percent,it)!!)
            }, {
                hideProgress()
                showMessage(it.localizedMessage!!)
            }, {
                hideProgress()
                liveDataDownloadFile.value = true
            })
    }

    fun resetFileDownloadStatus() {
        liveDataDownloadFile = MutableLiveData()
    }

    private fun returnMockDataForTestingPurposes(){
        val list: ArrayList<FileModel> = arrayListOf()
        list.add(FileModel(1,"VIDEO","https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4","Video 1"))
        list.add(FileModel(2,"VIDEO","https://bestvpn.org/html5demos/assets/dizzy.mp4","Video 2"))
        list.add(FileModel(3,"","https://kotlinlang.org/docs/kotlin-reference.pdf","PDF 3"))
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
    }
}