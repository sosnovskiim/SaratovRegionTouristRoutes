package com.sosnowskydevelop.tourroutessaratovregion.data

data class Region(
    private val _id: Long,
    private val _name: String,
    /* Images
    Map of the Saratov region, on which the region is highlighted.
    TODO private val _ratioMap: ?,
    TODO private val _emblem: ?,
    TODO private val _map: ?,
     */
) {
    val id: Long get() = _id
    val name: String get() = _name
    /*
    TODO val ratioMap: ? get() = _ratioMap
    TODO val emblem: ? get() = _emblem
    TODO val map: ? get() = _map
     */
}