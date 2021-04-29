package com.sosnowskydevelop.tourroutessaratovregion.utilities

import android.content.Context
import com.sosnowskydevelop.tourroutessaratovregion.data.RegionRepository
import com.sosnowskydevelop.tourroutessaratovregion.data.RoutePointRepository
import com.sosnowskydevelop.tourroutessaratovregion.data.RouteRepository
import com.sosnowskydevelop.tourroutessaratovregion.viewmodels.RegionListViewModelFactory
import com.sosnowskydevelop.tourroutessaratovregion.viewmodels.RouteDetailViewModelFactory
import com.sosnowskydevelop.tourroutessaratovregion.viewmodels.RouteListViewModelFactory
import com.sosnowskydevelop.tourroutessaratovregion.viewmodels.RouteMapViewModelFactory

object InjectorUtils {
    private fun getRegionRepository(context: Context) =
        RegionRepository.getInstance(context = context)

    private fun getRouteRepository() = RouteRepository.getInstance()

    private fun getRoutePointRepository() = RoutePointRepository.getInstance()

    fun provideRegionListViewModelFactory(context: Context) = RegionListViewModelFactory(
        regionRepository = getRegionRepository(context = context),
    )

    fun provideRouteListViewModelFactory(context: Context) = RouteListViewModelFactory(
        regionRepository = getRegionRepository(context = context),
        routeRepository = getRouteRepository(),
    )

    fun provideRouteDetailViewModelFactory() = RouteDetailViewModelFactory(
        routeRepository = getRouteRepository(),
    )

    fun provideRouteMapViewModelFactory() = RouteMapViewModelFactory(
        routePointRepository = getRoutePointRepository(),
    )
}