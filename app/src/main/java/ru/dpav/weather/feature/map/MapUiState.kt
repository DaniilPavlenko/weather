package ru.dpav.weather.feature.map

import ru.dpav.weather.core.model.CityWeather
import ru.dpav.weather.core.model.GeoCoordinate

data class MapUiState(
    val isLoading: Boolean = true,
    val requestedPosition: GeoCoordinate? = null,
    val citiesWeather: List<CityWeather> = emptyList(),
    val hasConnectionError: Boolean = false,
    val showTurnedOffGeoInfo: Boolean = false,
    val isLocationServicesAvailable: Boolean = false,
    val isLocationUpdating: Boolean = false,
)
