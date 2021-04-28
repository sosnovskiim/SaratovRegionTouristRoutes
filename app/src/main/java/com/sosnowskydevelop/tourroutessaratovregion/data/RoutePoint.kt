package com.sosnowskydevelop.tourroutessaratovregion.data

data class RoutePoint(
    private val _id: Long,
    private val _routeId: Long,
    private val _name: String? = null,
    /* Start/End/Intermediate
    TODO private val _type: ?,
     */
    private val _latitude: Double,
    private val _longitude: Double,
    private val _page: Int,
) {
    val id: Long get() = _id
    val routeId: Long get() = _routeId
    val name: String? get() = _name
    /*
    TODO val type: ? get() = _type
     */
    val latitude: Double get() = _latitude
    val longitude: Double get() = _longitude
    val page: Int get() = _page
}