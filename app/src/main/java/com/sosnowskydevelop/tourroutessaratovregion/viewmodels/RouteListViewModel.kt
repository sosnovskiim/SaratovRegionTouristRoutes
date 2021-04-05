package com.sosnowskydevelop.tourroutessaratovregion.viewmodels

import androidx.lifecycle.ViewModel
import com.sosnowskydevelop.tourroutessaratovregion.data.Route
import com.sosnowskydevelop.tourroutessaratovregion.data.RouteRepository

class RouteListViewModel(
        routeRepository: RouteRepository
) : ViewModel() {
    val routes: Array<Route> = routeRepository.getRoutes(/*TODO Pass the required id.*/ regionId = 22)
}