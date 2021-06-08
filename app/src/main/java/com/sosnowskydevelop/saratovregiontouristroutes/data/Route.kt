package com.sosnowskydevelop.saratovregiontouristroutes.data

data class Route(
    private val _id: Long,
    private val _regionId: Long,
    private val _name: String,
    private val _fileName: String,
) {
    val id: Long get() = _id
    val regionId: Long get() = _regionId
    val name: String get() = _name
    val fileName: String get() = _fileName
}