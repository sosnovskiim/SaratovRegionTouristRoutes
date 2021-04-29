package com.sosnowskydevelop.tourroutessaratovregion.data

data class Region(
    private val _id: Long,
    private val _name: String,
    private val _fileName: String,
) {
    val id: Long get() = _id
    val name: String get() = _name
    val fileName: String get() = _fileName
}