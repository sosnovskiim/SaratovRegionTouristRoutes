package com.sosnowskydevelop.tourroutessaratovregion.data

data class Route(
    private val _id: Long,
    private val _regionId: Long,
    private val _name: String,
    /* PDF File
    TODO private val _detail: ?,
     */
) {
    val id: Long get() = _id
    val regionId: Long get() = _regionId
    val name: String get() = _name
    /*
    TODO val detail: ? get() = _detail
     */
}