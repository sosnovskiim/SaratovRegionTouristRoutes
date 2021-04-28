package com.sosnowskydevelop.tourroutessaratovregion.viewmodels

import androidx.lifecycle.ViewModel
import com.sosnowskydevelop.tourroutessaratovregion.data.RoutePoint
import com.sosnowskydevelop.tourroutessaratovregion.data.RoutePointRepository

class RouteMapViewModel(
    private val routePointRepository: RoutePointRepository,
) : ViewModel() {
    lateinit var routeStartPoint: RoutePoint
    var routeIntermediatePoints: Array<RoutePoint>? = null
    var routeEndPoint: RoutePoint? = null

    fun initDetails(routeId: Long) {
        routeStartPoint = routePointRepository.getStartPoint(routeId = routeId)
        routeIntermediatePoints = routePointRepository.getIntermediatePoints(routeId = routeId)
        routeEndPoint = routePointRepository.getEndPoint(routeId = routeId)
    }
}