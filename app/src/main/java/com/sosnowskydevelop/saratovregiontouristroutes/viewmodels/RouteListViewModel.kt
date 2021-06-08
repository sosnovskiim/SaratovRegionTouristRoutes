package com.sosnowskydevelop.saratovregiontouristroutes.viewmodels

import androidx.lifecycle.ViewModel
import com.sosnowskydevelop.saratovregiontouristroutes.data.Region
import com.sosnowskydevelop.saratovregiontouristroutes.data.RegionRepository
import com.sosnowskydevelop.saratovregiontouristroutes.data.Route
import com.sosnowskydevelop.saratovregiontouristroutes.data.RouteRepository

class RouteListViewModel(
    private val regionRepository: RegionRepository,
    private val routeRepository: RouteRepository,
) : ViewModel() {
    private var region: Region? = null
    val regionId: Long? get() = region?.id
    val regionName: String? get() = region?.name
    val regionFileName: String? get() = region?.fileName
    var routes: Array<Route> = arrayOf()

    fun initRoutes(regionId: Long) {
        region = regionRepository.getRegion(regionId = regionId)
        routes = routeRepository.getRoutes(regionId = regionId)
    }
}