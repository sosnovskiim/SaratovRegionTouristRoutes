package com.sosnowskydevelop.tourroutessaratovregion.data

data class Route(
    private val _id: Long,
    private val _regionId: Long,
//    private val _authors: Array<String>,
//    private val _school: String,
//    private val _leaders: Array<String>? = null,
    private val _name: String,
    /* PDF File
    TODO private val _detail: ?,
     */
) {
    val id: Long get() = _id
    val regionId: Long get() = _regionId
//    val authors: Array<String> get() = _authors
//    val school: String get() = _school
//    val leaders: Array<String>? get() = _leaders
    val name: String get() = _name
    /*
    TODO val detail: ? get() = _detail
     */
}