package com.sosnowskydevelop.saratovregiontouristroutes.utilities

import android.content.Context
import com.sosnowskydevelop.saratovregiontouristroutes.data.RegionRepository
import com.sosnowskydevelop.saratovregiontouristroutes.data.RoutePointRepository
import com.sosnowskydevelop.saratovregiontouristroutes.data.RouteRepository
import com.sosnowskydevelop.saratovregiontouristroutes.viewmodels.*

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