package me.kolotilov.lifehackstudiowebapi.overview

import me.kolotilov.lifehackstudiowebapi.web.OverviewDto

data class OverviewData(
    val name: String,
    val imgSrc: String
)

fun OverviewDto.toOverviewData() = OverviewData(name, imgSrc)