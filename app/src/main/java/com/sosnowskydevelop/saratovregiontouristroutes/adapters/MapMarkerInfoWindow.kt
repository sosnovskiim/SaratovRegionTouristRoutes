package com.sosnowskydevelop.saratovregiontouristroutes.adapters

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import com.sosnowskydevelop.saratovregiontouristroutes.R
import com.sosnowskydevelop.saratovregiontouristroutes.data.RoutePoint
import com.sosnowskydevelop.saratovregiontouristroutes.utilities.LOG_TAG
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

        markerName.text = routePoint.name

        pointExport.setOnClickListener {
            val uri: Uri = Uri.parse(
                "yandexnavi://build_route_on_map?" +
                        "lat_to=${routePoint.latitude}" +
                        "&lon_to=${routePoint.longitude}"
            )
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
    }

    override fun onClose() {
        Log.i(LOG_TAG, "MapMarkerInfoWindow closed")
    }
}