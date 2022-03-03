package com.example.mvvm.model

import com.google.gson.annotations.SerializedName

data class FileModel(
        @SerializedName("id")
        val id: Int?,
        @SerializedName("type")
        val type: String?,
        @SerializedName("url")
        val url: String?,
        @SerializedName("name")
        val name: String?,
)


