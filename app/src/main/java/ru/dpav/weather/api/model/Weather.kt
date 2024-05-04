package ru.dpav.weather.api.model

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("id")
    val id: Int,
    @SerializedName("icon")
    val icon: String,
)
