package com.sosnowskydevelop.tourroutessaratovregion.viewmodels

import androidx.lifecycle.ViewModel
import com.sosnowskydevelop.tourroutessaratovregion.data.Route
import com.sosnowskydevelop.tourroutessaratovregion.data.RouteRepository

class RouteListViewModel(
        private val routeRepository: RouteRepository
) : ViewModel() {
    var routes: Array<Route> = routeRepository.getRoutes(regionId = 0)

    fun initRoutes(regionId: Long) {
        routes = routeRepository.getRoutes(regionId = regionId)
    }
}