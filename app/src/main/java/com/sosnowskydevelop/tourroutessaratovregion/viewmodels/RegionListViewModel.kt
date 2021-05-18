package com.sosnowskydevelop.tourroutessaratovregion.viewmodels

import androidx.lifecycle.ViewModel
import com.sosnowskydevelop.tourroutessaratovregion.data.Region
import com.sosnowskydevelop.tourroutessaratovregion.data.RegionRepository
import com.sosnowskydevelop.tourroutessaratovregion.data.Route
import com.sosnowskydevelop.tourroutessaratovregion.data.RouteRepository

class RegionListViewModel(
    regionRepository: RegionRepository,
    private val routeRepository: RouteRepository,
) : ViewModel() {
    var routes: Array<Route> = arrayOf()
    var regions: Array<Region> = regionRepository.getRegions()

    fun updateRoutes(searchQuery: String) {
        routes = routeRepository.getRoutesBySearchQuery(searchQuery = searchQuery)
    }
}