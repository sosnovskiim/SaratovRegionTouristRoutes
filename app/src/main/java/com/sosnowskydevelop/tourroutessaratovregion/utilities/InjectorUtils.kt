package com.sosnowskydevelop.tourroutessaratovregion.utilities

import android.content.Context
import com.sosnowskydevelop.tourroutessaratovregion.data.RegionRepository
import com.sosnowskydevelop.tourroutessaratovregion.data.RoutePointRepository
import com.sosnowskydevelop.tourroutessaratovregion.data.RouteRepository
import com.sosnowskydevelop.tourroutessaratovregion.viewmodels.*

object InjectorUtils {
    private fun getRegionRepository(context: Context) =
        RegionRepository.getInstance(context = context)

    private fun getRouteRepository(context: Context) =
        RouteRepository.getInstance(context = context)

    private fun getRoutePointRepository(context: Context) =
        RoutePointRepository.getInstance(context = context)

    fun provideRegionListViewModelFactory(context: Context) = RegionListViewModelFactory(
        regionRepository = getRegionRepository(context = context),
    )

    fun provideAnnotationViewModelFactory() = AnnotationViewModelFactory()

    fun provideRouteSearchViewModelFactory(context: Context) = RouteSearchViewModelFactory(
        routeRepository = getRouteRepository(context = context),
    )

    fun provideRouteListViewModelFactory(context: Context) = RouteListViewModelFactory(
        regionRepository = getRegionRepository(context = context),
        routeRepository = getRouteRepository(context = context),
    )

    fun provideRouteDetailViewModelFactory(context: Context) = RouteDetailViewModelFactory(
        routeRepository = getRouteRepository(context = context),
    )

    fun provideRouteMapViewModelFactory(context: Context) = RouteMapViewModelFactory(
        routePointRepository = getRoutePointRepository(context = context),
    )
}