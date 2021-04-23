package com.sosnowskydevelop.tourroutessaratovregion.adapters

import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.sosnowskydevelop.tourroutessaratovregion.R
import com.sosnowskydevelop.tourroutessaratovregion.utilities.BUNDLE_KEY_PAGE_ROUTE_MAP_TO_ROUTE_DETAIL
import com.sosnowskydevelop.tourroutessaratovregion.utilities.BUNDLE_KEY_ROUTE_ID_ROUTE_LIST_TO_ROUTE_DETAIL
import com.sosnowskydevelop.tourroutessaratovregion.utilities.LOG_TAG
import com.sosnowskydevelop.tourroutessaratovregion.utilities.REQUEST_KEY_PAGE_ROUTE_MAP_TO_ROUTE_DETAIL
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.infowindow.InfoWindow

class MapMarkerInfoWindow(
    mapView: MapView,
    private val fragment: Fragment,
    private val routePointName: String?,
    private val routePointPage: Int,
) : InfoWindow(R.layout.info_window_map_marker, mapView) {
    override fun onOpen(item: Any?) {
        closeAllInfoWindowsOn(mapView)

        val markerName: TextView = mView.findViewById(R.id.markerName)
        val gotoDetail: Button = mView.findViewById(R.id.gotoDetail)

        if (routePointName != null) {
            markerName.text = routePointName
        } else {
            markerName.text = "Без названия"
        }

        gotoDetail.setOnClickListener {
            fragment.setFragmentResult(
                requestKey = REQUEST_KEY_PAGE_ROUTE_MAP_TO_ROUTE_DETAIL,
                result = bundleOf(
                    BUNDLE_KEY_PAGE_ROUTE_MAP_TO_ROUTE_DETAIL to routePointPage
                )
            )
            fragment.findNavController()
                .navigate(R.id.action_routeMapFragment_to_routeDetailFragment)
        }
    }

    override fun onClose() {
        Log.i(LOG_TAG, "MapMarkerInfoWindow closed")
    }
}