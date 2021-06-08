package com.sosnowskydevelop.saratovregiontouristroutes.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sosnowskydevelop.saratovregiontouristroutes.data.RoutePointRepository

class RouteMapViewModelFactory(
    private val routePointRepository: RoutePointRepository,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RouteMapViewModel(
            routePointRepository = routePointRepository,
        ) as T
    }
}