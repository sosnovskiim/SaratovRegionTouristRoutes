package com.sosnowskydevelop.tourroutessaratovregion.viewmodels

import androidx.lifecycle.ViewModel
import com.sosnowskydevelop.tourroutessaratovregion.data.Region
import com.sosnowskydevelop.tourroutessaratovregion.data.RegionRepository
import com.sosnowskydevelop.tourroutessaratovregion.data.Route
import com.sosnowskydevelop.tourroutessaratovregion.data.RouteRepository

class RouteListViewModel(
    private val regionRepository: RegionRepository,
    private val routeRepository: RouteRepository,
) : ViewModel() {
    private var region: Region? = null
    val regionName: String? get() = region?.name
    var routes: Array<Route> = routeRepository.getRoutes(regionId = 0)

    fun initRoutes(regionId: Long) {
        region = regionRepository.getRegion(regionId = regionId)
        routes = routeRepository.getRoutes(regionId = regionId)
    }
}