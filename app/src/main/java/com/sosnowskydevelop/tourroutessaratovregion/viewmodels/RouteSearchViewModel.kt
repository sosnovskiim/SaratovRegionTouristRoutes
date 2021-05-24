package com.sosnowskydevelop.tourroutessaratovregion.viewmodels

import androidx.lifecycle.ViewModel
import com.sosnowskydevelop.tourroutessaratovregion.data.Route
import com.sosnowskydevelop.tourroutessaratovregion.data.RouteRepository

class RouteSearchViewModel(
    private val routeRepository: RouteRepository,
) : ViewModel() {
    var regionId: Long = 0
    var routes: Array<Route> = arrayOf()

    fun updateRoutes(searchString: String, isSearchByRoutePoints: Boolean) {
        routes = routeRepository.getRoutesBySearchString(
            searchString = searchString,
            isSearchByRoutePoints = isSearchByRoutePoints,
            regionId = regionId,
        )
    }
}