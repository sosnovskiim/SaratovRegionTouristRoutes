package com.sosnowskydevelop.tourroutessaratovregion.viewmodels

import androidx.lifecycle.ViewModel
import com.sosnowskydevelop.tourroutessaratovregion.data.Route
import com.sosnowskydevelop.tourroutessaratovregion.data.RouteRepository

class RouteSearchViewModel(
    private val routeRepository: RouteRepository,
) : ViewModel() {
    var searchQuery: String = ""
    var routes: Array<Route> = arrayOf()

    fun notifySearchQueryChanged() {
        routes = if (searchQuery != "") {
            routeRepository.getRoutesBySearchQuery(searchQuery = searchQuery)
        } else {
            arrayOf()
        }
    }
}