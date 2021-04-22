package com.sosnowskydevelop.tourroutessaratovregion.viewmodels

import androidx.lifecycle.ViewModel
import com.sosnowskydevelop.tourroutessaratovregion.data.RouteMap
import com.sosnowskydevelop.tourroutessaratovregion.data.RoutePoint
import com.sosnowskydevelop.tourroutessaratovregion.data.RouteRepository

class RouteMapViewModel(
    private val routeRepository: RouteRepository,
) : ViewModel() {
    private lateinit var routeMap: RouteMap

    val routeStartPoint: RoutePoint get() = routeMap.startPoint
    val routeIntermediatePoints: Array<RoutePoint>? get() = routeMap.intermediatePoints
    val routeEndPoint: RoutePoint? get() = routeMap.endPoint

    fun initDetails(routeId: Long) {
        routeMap = routeRepository.getRouteMap(routeId = routeId)!!
    }
}