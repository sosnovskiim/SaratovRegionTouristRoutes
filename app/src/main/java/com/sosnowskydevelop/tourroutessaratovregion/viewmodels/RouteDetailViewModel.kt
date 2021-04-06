package com.sosnowskydevelop.tourroutessaratovregion.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.sosnowskydevelop.tourroutessaratovregion.data.Route
import com.sosnowskydevelop.tourroutessaratovregion.data.RouteRepository

class RouteDetailViewModel(
        private val routeRepository: RouteRepository
) : ViewModel() {
    private lateinit var route: Route

    val routeName: ObservableField<String> = ObservableField()

    fun initDetails(routeId: Long) {
        route = routeRepository.getRoute(routeId = routeId)!!
        routeName.set(route.name)
    }
}