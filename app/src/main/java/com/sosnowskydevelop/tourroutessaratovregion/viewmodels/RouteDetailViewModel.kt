package com.sosnowskydevelop.tourroutessaratovregion.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.sosnowskydevelop.tourroutessaratovregion.data.Route
import com.sosnowskydevelop.tourroutessaratovregion.data.RoutePoint
import com.sosnowskydevelop.tourroutessaratovregion.data.RouteRepository
import com.sosnowskydevelop.tourroutessaratovregion.data.RouteType

class RouteDetailViewModel(
        private val routeRepository: RouteRepository
) : ViewModel() {
    private lateinit var route: Route

    val routeName: ObservableField<String> = ObservableField()

    val routeType: RouteType get() = route.type

    val routeStartPoint: RoutePoint get() = route.startPoint
    val routeIntermediatePoints: Array<RoutePoint>? get() = route.intermediatePoints
    val routeEndPoint: RoutePoint? get() = route.endPoint

    fun initDetails(routeId: Long) {
        route = routeRepository.getRoute(routeId = routeId)!!
        routeName.set(route.name)
    }
}