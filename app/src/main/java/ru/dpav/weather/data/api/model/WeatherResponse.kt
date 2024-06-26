package ru.dpav.weather.data.api.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("list")
    val cities: List<City>,
)
