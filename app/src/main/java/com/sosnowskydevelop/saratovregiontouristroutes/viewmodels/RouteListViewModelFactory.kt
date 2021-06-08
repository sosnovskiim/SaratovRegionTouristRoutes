package com.sosnowskydevelop.saratovregiontouristroutes.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sosnowskydevelop.saratovregiontouristroutes.data.RegionRepository
import com.sosnowskydevelop.saratovregiontouristroutes.data.RouteRepository

class RouteListViewModelFactory(
    private val regionRepository: RegionRepository,
    private val routeRepository: RouteRepository,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RouteListViewModel(
            regionRepository = regionRepository,
            routeRepository = routeRepository,
        ) as T
    }
}