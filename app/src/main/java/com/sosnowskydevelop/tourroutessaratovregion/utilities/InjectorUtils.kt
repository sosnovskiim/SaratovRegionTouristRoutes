package com.sosnowskydevelop.tourroutessaratovregion.utilities

import com.sosnowskydevelop.tourroutessaratovregion.data.RegionRepository
import com.sosnowskydevelop.tourroutessaratovregion.viewmodels.RegionListViewModelFactory

object InjectorUtils {
    private fun getRegionRepository() = RegionRepository.getInstance()

    fun provideRegionListViewModelFactory() =
        RegionListViewModelFactory(regionRepository = getRegionRepository())
}