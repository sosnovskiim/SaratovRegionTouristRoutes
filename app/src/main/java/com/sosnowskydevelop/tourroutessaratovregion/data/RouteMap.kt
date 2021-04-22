package com.sosnowskydevelop.tourroutessaratovregion.data

data class RouteMap(
    private val _id: Long,
    private val _routeId: Long,
    private val _startPoint: RoutePoint,
    private val _endPoint: RoutePoint? = null,
    private val _intermediatePoints: Array<RoutePoint>? = null,
) {
    val id: Long get() = _id
    val routeId: Long get() = _routeId
    val startPoint: RoutePoint get() = _startPoint
    val endPoint: RoutePoint? get() = _endPoint
    val intermediatePoints: Array<RoutePoint>? get() = _intermediatePoints
}