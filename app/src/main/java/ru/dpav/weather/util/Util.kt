package ru.dpav.weather.util

import android.app.Activity
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import ru.dpav.weather.R

class Util {
    class Icons {
        companion object {
            fun getWeatherIconByName(iconName: String) =
                when (iconName) {
                    "01d" -> R.drawable.weather_icon_01
                    "01n" -> R.drawable.weather_icon_01n
                    "02d" -> R.drawable.weather_icon_02
                    "02n" -> R.drawable.weather_icon_02n
                    "03d", "03n" -> R.drawable.weather_icon_03
                    "04d", "04n" -> R.drawable.weather_icon_04
                    "09d", "09n" -> R.drawable.weather_icon_09
                    "10d" -> R.drawable.weather_icon_10
                    "10n" -> R.drawable.weather_icon_10n
                    "11d", "11n" -> R.drawable.weather_icon_11
                    "13d", "13n" -> R.drawable.weather_icon_13
                    "50d" -> R.drawable.weather_icon_50
                    else -> R.drawable.weather_icon_01
                }
        }
    }

    companion object {
        private const val PLAY_SERVICES_CODE = 2404

        fun isGooglePlayAvailable(activity: Activity): Boolean {
            val googleApi = GoogleApiAvailability.getInstance()
            val status: Int = googleApi.isGooglePlayServicesAvailable(activity)
            if (status != ConnectionResult.SUCCESS) {
                if (googleApi.isUserResolvableError(status)) {
                    googleApi.getErrorDialog(
                        activity,
                        status,
                        PLAY_SERVICES_CODE
                    ).show()
                }
                return false
            }
            return true
        }
    }
}
