package com.sosnowskydevelop.saratovregiontouristroutes.viewmodels

import androidx.lifecycle.ViewModel
import com.sosnowskydevelop.saratovregiontouristroutes.data.Region
import com.sosnowskydevelop.saratovregiontouristroutes.data.RegionRepository

class RegionListViewModel(
    regionRepository: RegionRepository,
) : ViewModel() {
    var regions: Array<Region> = regionRepository.getRegions()
}