package ru.dpav.weather

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.arellomobile.mvp.MvpDelegate
import ru.dpav.weather.api.City
import ru.dpav.weather.presenters.ListPresenter

class CitiesAdapter(
	private var mListPresenter: ListPresenter,
	private var mMvpDelegate: MvpDelegate<*>
) : RecyclerView.Adapter<CityHolder>() {

	private var mCities: ArrayList<City> = arrayListOf()
	private var mOpenedPosition = -1

	override fun onCreateViewHolder(
		parent: ViewGroup,
		position: Int
	): CityHolder {
		val view = LayoutInflater.from(parent.context)
			.inflate(R.layout.item_row_city, parent, false)
		return CityHolder(view, mMvpDelegate, mListPresenter)
	}

	override fun getItemCount(): Int {
		return mCities.count()
	}

	override fun onBindViewHolder(
		holder: CityHolder,
		position: Int
	) {
		val city = mCities[position]
		holder.bind(city, position == mOpenedPosition)
	}

	fun hideDropDownInfo() {
		mOpenedPosition = -1
	}

	fun showDropDownInfo(position: Int) {
		this.notifyItemChanged(mOpenedPosition)
		mOpenedPosition = position
	}

	fun setCities(newCities: List<City>) {
		hideDropDownInfo()
		mCities.clear()
		mCities.addAll(newCities)
		notifyDataSetChanged()
	}
}