package com.example.mvvm.domain.home

import com.example.mvvm.model.FileModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface HomeServices {

    @get:GET("movies")
    val getListOfFiles: Observable<ArrayList<FileModel>?>?
}