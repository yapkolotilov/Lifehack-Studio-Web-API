package me.kolotilov.lifehackstudiowebapi.details

import me.kolotilov.lifehackstudiowebapi.web.DetailsDto

data class DetailsData(
    val id: Long,
    val name: String,
    val imgSrc: String,
    val description: String,
    val latitude: Float,
    val longitude: Float,
    val url: String,
    val phone: String
)

fun DetailsDto.toDetailsData() = DetailsData(id, name, imgSrc, description, latitude, longitude, url, phone)