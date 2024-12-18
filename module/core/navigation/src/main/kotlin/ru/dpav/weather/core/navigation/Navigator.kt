package ru.dpav.weather.core.navigation

import androidx.fragment.app.Fragment

interface Navigator {
    fun Fragment.navigateTo(
        destinationFragment: Fragment,
        tag: String,
        shouldAddToBackStack: Boolean = true,
    )

    fun Fragment.navigateBack()

    fun Fragment.navigateBackToRoot()
}

fun Fragment.findNavigator(): Navigator {
    val activity = requireActivity()
    check(activity is NavigatorProvider) { "$activity must implement NavigatorProvider." }
    return activity.provideNavigator()
}
