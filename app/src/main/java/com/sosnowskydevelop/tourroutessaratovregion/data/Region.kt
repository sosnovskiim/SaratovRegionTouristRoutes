package com.sosnowskydevelop.tourroutessaratovregion.data

data class Region(
    private val _name: String,
) {
    val name: String get() = _name
}