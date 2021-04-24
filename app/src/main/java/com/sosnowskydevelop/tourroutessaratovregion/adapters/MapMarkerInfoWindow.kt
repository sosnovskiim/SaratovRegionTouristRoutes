package com.sosnowskydevelop.tourroutessaratovregion.adapters

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.sosnowskydevelop.tourroutessaratovregion.R
import com.sosnowskydevelop.tourroutessaratovregion.data.RoutePoint
import com.sosnowskydevelop.tourroutessaratovregion.utilities.BUNDLE_KEY_PAGE_ROUTE_MAP_TO_ROUTE_DETAIL
import com.sosnowskydevelop.tourroutessaratovregion.utilities.LOG_TAG
import com.sosnowskydevelop.tourroutessaratovregion.utilities.REQUEST_KEY_PAGE_ROUTE_MAP_TO_ROUTE_DETAIL
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.infowindow.InfoWindow

class MapMarkerInfoWindow(
    mapView: MapView,
    private val fragment: Fragment,
    private val routePoint: RoutePoint,
) : InfoWindow(R.layout.info_window_map_marker, mapView) {
    override fun onOpen(item: Any?) {
        closeAllInfoWindowsOn(mapView)

        val markerName: TextView = mView.findViewById(R.id.markerName)
        val pointExport: Button = mView.findViewById(R.id.pointExport)
        val gotoDetail: Button = mView.findViewById(R.id.gotoDetail)

        if (routePoint.name != null) {
            markerName.text = routePoint.name
        } else {
            markerName.text = "Без названия"
        }

        pointExport.setOnClickListener {
            val uri: Uri = Uri.parse("yandexnavi://build_route_on_map?lat_to=${routePoint.latitude}&lon_to=${routePoint.longitude}")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            try {
                startActivity(fragment.requireContext(), intent, null)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(
                    fragment.requireContext(),
                    "Приложение Яндекс.Навигатор не найдено",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        gotoDetail.setOnClickListener {
            fragment.setFragmentResult(
                requestKey = REQUEST_KEY_PAGE_ROUTE_MAP_TO_ROUTE_DETAIL,
                result = bundleOf(
                    BUNDLE_KEY_PAGE_ROUTE_MAP_TO_ROUTE_DETAIL to routePoint.page
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