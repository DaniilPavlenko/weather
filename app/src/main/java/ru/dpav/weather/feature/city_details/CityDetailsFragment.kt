package ru.dpav.weather.feature.city_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.dpav.weather.R
import ru.dpav.weather.data.WeatherRepository
import ru.dpav.weather.databinding.FragmentCityDetailsBinding
import ru.dpav.weather.ui.WeatherIconAssociator
import ru.dpav.weather.ui.extension.popBackStackToRoot

class CityDetailsFragment : Fragment(R.layout.fragment_city_details) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCityDetailsBinding.bind(view)

        val cityId = requireNotNull(arguments?.getInt(ARG_CITY_ID))
        val city = WeatherRepository.cities.firstOrNull { it.id == cityId }
        if (city == null) {
            // Just back to the root fragment (map).
            parentFragmentManager.popBackStackToRoot()
            return
        }

        with(binding) {
            with(toolbar) {
                title = city.name
                setNavigationOnClickListener { parentFragmentManager.popBackStack() }
            }
            with(cityDetailTemperature) {
                text = getString(R.string.detail_temperature, city.main.temp.toInt())
                val icon = WeatherIconAssociator.getIconByName(city.weather[0].icon)
                setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0)
            }
            cityDetailWind.text = getString(R.string.detail_wind, city.wind.speed.toInt())
            cityDetailCloudy.text = getString(R.string.detail_cloudy, city.clouds.cloudy)
            cityDetailPressure.text = getString(R.string.detail_pressure, city.main.pressureInMmHg)
            cityDetailHumidity.text = getString(
                R.string.detail_humidity,
                city.main.humidity.toInt()
            )
        }
    }

    companion object {
        private const val ARG_CITY_ID = "city_id"

        fun newInstance(cityId: Int) = CityDetailsFragment().apply {
            arguments = Bundle(1).apply {
                putInt(ARG_CITY_ID, cityId)
            }
        }
    }
}
