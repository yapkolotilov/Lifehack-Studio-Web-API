package me.kolotilov.lifehackstudiowebapi.overview

import me.kolotilov.lifehackstudiowebapi.web.OverviewDto

data class OverviewData(
    val id: Long,
    val name: String,
    val imgSrc: String
)

fun OverviewDto.toOverviewData() = OverviewData(id, name, imgSrc)