package com.sosnowskydevelop.tourroutessaratovregion.data

data class Route(
        private val _id: Long,
        private val _regionId: Long,
        private val _authors: Array<String>,
        private val _school: String,
        private val _leaders: Array<String>? = null,
        private val _name: String,
        private val _type: RouteType,
        private val _startPoint: RoutePoint,
        private val _endPoint: RoutePoint? = null,
        private val _intermediatePoints: Array<RoutePoint>? = null,
) {
    val id: Long get() = _id
    val parentId: Long get() = _regionId
    val authors: Array<String> get() = _authors
    val school: String get() = _school
    val leaders: Array<String>? get() = _leaders
    val name: String get() = _name
    val type: RouteType get() = _type
    val startPoint: RoutePoint get() = _startPoint
    val endPoint: RoutePoint? get() = _endPoint
    val intermediatePoints: Array<RoutePoint>? get() = _intermediatePoints
}

data class RoutePoint(
        private val _latitude: Double,
        private val _longitude: Double,
        private val _name: String,
) {
    val latitude: Double get() = _latitude
    val longitude: Double get() = _longitude
    val name: String get() = _name
}

enum class RouteType {
    LINEAR, NONLINEAR
}
