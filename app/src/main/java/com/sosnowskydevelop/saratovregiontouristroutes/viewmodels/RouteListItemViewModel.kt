package com.sosnowskydevelop.saratovregiontouristroutes.viewmodels

import androidx.lifecycle.ViewModel
import com.sosnowskydevelop.saratovregiontouristroutes.data.Route

class RouteListItemViewModel(
    private val route: Route,
) : ViewModel() {
    val routeName: String
        get() {
            return route.name
        }
}