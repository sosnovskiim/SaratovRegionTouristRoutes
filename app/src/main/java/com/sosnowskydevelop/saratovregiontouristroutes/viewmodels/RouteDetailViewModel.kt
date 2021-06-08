package com.sosnowskydevelop.saratovregiontouristroutes.viewmodels

import androidx.lifecycle.ViewModel
import com.sosnowskydevelop.saratovregiontouristroutes.data.Route
import com.sosnowskydevelop.saratovregiontouristroutes.data.RouteRepository

class RouteDetailViewModel(
    private val routeRepository: RouteRepository,
) : ViewModel() {
    private var route: Route? = null
    val routeId: Long? get() = route?.id
    val routeName: String? get() = route?.name
    val routeFileName: String? get() = route?.fileName

    fun initDetails(routeId: Long) {
        route = routeRepository.getRoute(routeId = routeId)
    }
}