package com.example.nagwainterview.domain.main

import com.example.nagwainterview.model.FileModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface MainServices {

    @get:GET("movies")
    val getListOfFiles: Observable<ArrayList<FileModel>?>?
}