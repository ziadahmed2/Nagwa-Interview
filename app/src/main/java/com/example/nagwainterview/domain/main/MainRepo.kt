package com.example.nagwainterview.domain.main

import com.example.nagwainterview.model.FileModel
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepo
@Inject constructor(@JvmField @Inject var services: MainServices?){

    fun getListOfFiles(): Observable<ArrayList<FileModel>?>?{
        return services?.getListOfFiles
    }
}