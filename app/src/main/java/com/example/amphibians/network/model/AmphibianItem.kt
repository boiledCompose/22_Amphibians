package com.example.amphibians.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AmphibianItem (
    val name:String,
    val type:String,
    val description:String,
    @SerialName("img_src")
    val imgSrc:String
)