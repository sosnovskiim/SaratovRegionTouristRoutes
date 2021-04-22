package com.sosnowskydevelop.tourroutessaratovregion.viewmodels

import androidx.lifecycle.ViewModel
import com.sosnowskydevelop.tourroutessaratovregion.data.Region
import com.sosnowskydevelop.tourroutessaratovregion.data.RegionRepository

class RegionListViewModel(
    regionRepository: RegionRepository,
) : ViewModel() {
    val regions: Array<Region> = regionRepository.getRegions()
}