package me.kolotilov.lifehackstudiowebapi.web

import com.google.gson.annotations.SerializedName

data class OverviewDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("img")
    val imgSrc: String
)