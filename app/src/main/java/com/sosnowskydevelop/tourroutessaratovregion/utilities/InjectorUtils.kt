package com.sosnowskydevelop.tourroutessaratovregion.utilities

import com.sosnowskydevelop.tourroutessaratovregion.data.RegionRepository
import com.sosnowskydevelop.tourroutessaratovregion.data.RouteRepository
import com.sosnowskydevelop.tourroutessaratovregion.viewmodels.RegionListViewModelFactory
import com.sosnowskydevelop.tourroutessaratovregion.viewmodels.RouteDetailViewModelFactory
import com.sosnowskydevelop.tourroutessaratovregion.viewmodels.RouteListViewModelFactory

object InjectorUtils {
    private fun getRegionRepository() = RegionRepository.getInstance()

    private fun getRouteRepository() = RouteRepository.getInstance()

    fun provideRegionListViewModelFactory() =
            RegionListViewModelFactory(regionRepository = getRegionRepository())

    fun provideRouteListViewModelFactory() =
            RouteListViewModelFactory(routeRepository = getRouteRepository())

    fun provideRouteDetailViewModelFactory() =
            RouteDetailViewModelFactory(routeRepository = getRouteRepository())
}