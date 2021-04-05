package com.sosnowskydevelop.tourroutessaratovregion.viewmodels

import androidx.lifecycle.ViewModel
import com.sosnowskydevelop.tourroutessaratovregion.data.Route

class RouteListItemViewModel(
        private val route: Route
) : ViewModel() {
    val routeName: String
        get() {
            return route.name
        }
}