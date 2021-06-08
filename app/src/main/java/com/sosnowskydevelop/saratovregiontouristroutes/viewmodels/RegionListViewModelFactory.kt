package com.sosnowskydevelop.saratovregiontouristroutes.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sosnowskydevelop.saratovregiontouristroutes.data.RegionRepository

class RegionListViewModelFactory(
    private val regionRepository: RegionRepository,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegionListViewModel(
            regionRepository = regionRepository,
        ) as T
    }
}