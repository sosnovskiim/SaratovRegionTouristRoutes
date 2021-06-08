package com.sosnowskydevelop.saratovregiontouristroutes.viewmodels

import androidx.lifecycle.ViewModel
import com.sosnowskydevelop.saratovregiontouristroutes.data.RoutePoint
import com.sosnowskydevelop.saratovregiontouristroutes.data.RoutePointRepository

class RouteMapViewModel(
    private val routePointRepository: RoutePointRepository,
) : ViewModel() {
    private lateinit var routePoints: Array<RoutePoint>
    val routeStartPoint: RoutePoint get() = routePoints.first()
    val routeIntermediatePoints: Array<RoutePoint>?
        get() {
            return if (routePoints.size > 2) {
                routePoints.copyOfRange(1, routePoints.lastIndex)
            } else {
                null
            }
        }
    val routeEndPoint: RoutePoint?
        get() {
            return if (routePoints.size > 1) {
                routePoints.last()
            } else {
                null
            }
        }

    fun initDetails(routeId: Long) {
        routePoints = routePointRepository.getRoutePoints(routeId = routeId)
    }
}