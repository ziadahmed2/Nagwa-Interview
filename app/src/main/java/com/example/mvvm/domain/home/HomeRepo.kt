package com.example.mvvm.domain.home

import com.example.mvvm.model.FileModel
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepo
@Inject constructor(@JvmField @Inject var services: HomeServices?){

    fun getListOfFiles(): Observable<ArrayList<FileModel>?>?{
        return services?.getListOfFiles
    }
}