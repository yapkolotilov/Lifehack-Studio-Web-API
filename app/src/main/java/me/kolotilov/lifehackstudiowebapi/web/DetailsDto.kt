package me.kolotilov.lifehackstudiowebapi.web

import com.google.gson.annotations.SerializedName

data class DetailsDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("img")
    val imgSrc: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("lat")
    val latitude: String,
    @SerializedName("lon")
    val longitude: String,
    @SerializedName("www")
    val url: String,
    @SerializedName("phone")
    val phone: String
)