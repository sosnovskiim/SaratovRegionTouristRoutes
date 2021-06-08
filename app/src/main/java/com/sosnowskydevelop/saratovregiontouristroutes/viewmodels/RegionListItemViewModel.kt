package com.sosnowskydevelop.saratovregiontouristroutes.viewmodels

import androidx.lifecycle.ViewModel
import com.sosnowskydevelop.saratovregiontouristroutes.data.Region

class RegionListItemViewModel(
    private val region: Region,
) : ViewModel() {
    val regionName: String
        get() {
            return region.name
        }
}