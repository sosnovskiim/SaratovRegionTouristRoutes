package com.sosnowskydevelop.tourroutessaratovregion.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.sosnowskydevelop.tourroutessaratovregion.data.Route
import com.sosnowskydevelop.tourroutessaratovregion.data.RouteRepository

class RouteDetailViewModel(
    private val routeRepository: RouteRepository,
) : ViewModel() {
    private lateinit var route: Route

    var routeId: Long = 0

    val routeName: ObservableField<String> = ObservableField()

    fun initDetails(routeId: Long) {
        route = routeRepository.getRoute(routeId = routeId)!!
        this.routeId = route.id
        routeName.set(route.name)
    }
}