package com.sosnowskydevelop.tourroutessaratovregion.data

data class Region(
    private val _id: Long,
    private val _name: String,
) {
    val id: Long get() = _id
    val name: String get() = _name
}