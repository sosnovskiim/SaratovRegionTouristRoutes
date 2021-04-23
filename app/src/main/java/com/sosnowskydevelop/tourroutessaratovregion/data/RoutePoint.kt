package com.sosnowskydevelop.tourroutessaratovregion.data

data class RoutePoint(
    private val _id: Long,
    private val _routeId: Long,
    private val _latitude: Double,
    private val _longitude: Double,
    private val _name: String? = null,
    private val _page: Int,
) {
    val id: Long get() = _id
    val routeId: Long get() = _routeId
    val latitude: Double get() = _latitude
    val longitude: Double get() = _longitude
    val name: String? get() = _name
    val page: Int get() = _page
}