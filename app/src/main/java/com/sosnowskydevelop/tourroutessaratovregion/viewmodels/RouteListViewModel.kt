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
    val regionId: Long? get() = region?.id
    val regionName: String? get() = region?.name
    val regionFileName: String? get() = region?.fileName
    var routes: Array<Route> = arrayOf()

    fun initRoutes(regionId: Long) {
        region = regionRepository.getRegion(regionId = regionId)
        routes = routeRepository.getRoutes(regionId = regionId)
    }
}