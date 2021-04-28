package com.sosnowskydevelop.tourroutessaratovregion.viewmodels

import androidx.lifecycle.ViewModel
import com.sosnowskydevelop.tourroutessaratovregion.data.Route
import com.sosnowskydevelop.tourroutessaratovregion.data.RouteRepository

class RouteDetailViewModel(
    private val routeRepository: RouteRepository,
) : ViewModel() {
    private lateinit var route: Route
    val routeId: Long get() = route.id
    val routeName: String get() = route.name

    fun initDetails(routeId: Long) {
        route = routeRepository.getRoute(routeId = routeId)!!
    }
}