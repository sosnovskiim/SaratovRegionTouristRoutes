package com.sosnowskydevelop.tourroutessaratovregion.viewmodels

import androidx.lifecycle.ViewModel
import com.sosnowskydevelop.tourroutessaratovregion.data.Region

class RegionListItemViewModel(
    private val region: Region
) : ViewModel() {
    val regionName: String
        get() {
            return region.name
        }
}