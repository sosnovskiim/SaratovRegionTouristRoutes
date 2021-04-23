package com.sosnowskydevelop.tourroutessaratovregion.data

import org.osmdroid.views.MapView

data class Route(
    private val _id: Long,
    private val _regionId: Long,
    private val _authors: Array<String>,
    private val _school: String,
    private val _leaders: Array<String>? = null,
    private val _name: String,
) {
    val id: Long get() = _id
    val regionId: Long get() = _regionId
    val authors: Array<String> get() = _authors
    val school: String get() = _school
    val leaders: Array<String>? get() = _leaders
    val name: String get() = _name
}