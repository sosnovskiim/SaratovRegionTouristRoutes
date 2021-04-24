package com.sosnowskydevelop.tourroutessaratovregion.fragments

import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.sosnowskydevelop.tourroutessaratovregion.adapters.MapMarkerInfoWindow
import com.sosnowskydevelop.tourroutessaratovregion.data.RoutePoint
import com.sosnowskydevelop.tourroutessaratovregion.databinding.FragmentRouteMapBinding
import com.sosnowskydevelop.tourroutessaratovregion.utilities.BUNDLE_KEY_ROUTE_ID_ROUTE_DETAIL_TO_ROUTE_MAP
import com.sosnowskydevelop.tourroutessaratovregion.utilities.InjectorUtils
import com.sosnowskydevelop.tourroutessaratovregion.utilities.REQUEST_KEY_ROUTE_ID_ROUTE_DETAIL_TO_ROUTE_MAP
import com.sosnowskydevelop.tourroutessaratovregion.viewmodels.RouteMapViewModel
import org.osmdroid.api.IMapController
import org.osmdroid.config.Configuration
import org.osmdroid.events.MapEventsReceiver
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.MapEventsOverlay
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.infowindow.InfoWindow

class RouteMapFragment : Fragment(), MapEventsReceiver {
    private lateinit var fragmentRouteMapBinding: FragmentRouteMapBinding

    private val routeMapViewModel: RouteMapViewModel by viewModels {
        InjectorUtils.provideRouteMapViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentRouteMapBinding =
            FragmentRouteMapBinding.inflate(inflater, container, false)
        return fragmentRouteMapBinding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener(
            requestKey = REQUEST_KEY_ROUTE_ID_ROUTE_DETAIL_TO_ROUTE_MAP
        ) { _, bundle ->
            val routeId: Long = bundle.getLong(BUNDLE_KEY_ROUTE_ID_ROUTE_DETAIL_TO_ROUTE_MAP)
            routeMapViewModel.initDetails(routeId = routeId)
            initMap()
        }
    }

    private lateinit var mapController: IMapController
    private val geoPoints: MutableList<GeoPoint> = mutableListOf()

    @RequiresApi(Build.VERSION_CODES.N)
    private fun initMap() {
        initPreferences()

        val mapEventsOverlay = MapEventsOverlay(this)
        fragmentRouteMapBinding.routeMap.overlays.add(0, mapEventsOverlay)
        fragmentRouteMapBinding.routeMap.setTileSource(TileSourceFactory.MAPNIK)
        fragmentRouteMapBinding.routeMap.setBuiltInZoomControls(true)
        fragmentRouteMapBinding.routeMap.setMultiTouchControls(true)

        mapController = fragmentRouteMapBinding.routeMap.controller
        mapController.setZoom(12.0)

        val routeStartPoint: RoutePoint = routeMapViewModel.routeStartPoint
        addMarkerToMap(
            isStartPoint = true,
            routePoint = routeStartPoint,
        )

        val routeIntermediatePoints: Array<RoutePoint>? =
            routeMapViewModel.routeIntermediatePoints
        routeIntermediatePoints?.forEach { routePoint ->
            addMarkerToMap(
                routePoint = routePoint,
            )
        }

        val routeEndPoint: RoutePoint? = routeMapViewModel.routeEndPoint
        if (routeEndPoint != null) {
            addMarkerToMap(
                routePoint = routeEndPoint,
            )
        }
    }

    private fun initPreferences() {
        @Suppress("DEPRECATION")
        Configuration.getInstance().load(
            requireActivity().applicationContext, PreferenceManager.getDefaultSharedPreferences(
                requireActivity().applicationContext
            )
        )
    }

    private fun addMarkerToMap(
        isStartPoint: Boolean = false,
        routePoint: RoutePoint,
    ) {
        val routeGeoPoint = GeoPoint(routePoint.latitude, routePoint.longitude)
        geoPoints.add(routeGeoPoint)
        val marker = Marker(fragmentRouteMapBinding.routeMap)
        marker.position = routeGeoPoint
        /*TODO Experiment with Marker.setAnchor().*/
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
        marker.infoWindow = MapMarkerInfoWindow(
            mapView = fragmentRouteMapBinding.routeMap,
            fragment = this,
            routePoint = routePoint,
        )
        fragmentRouteMapBinding.routeMap.overlays.add(marker)
        fragmentRouteMapBinding.routeMap.invalidate()
        if (isStartPoint) mapController.setCenter(routeGeoPoint)
    }

    override fun onResume() {
        super.onResume()
        fragmentRouteMapBinding.routeMap.onResume()
    }

    override fun onPause() {
        super.onPause()
        fragmentRouteMapBinding.routeMap.onPause()
    }

    override fun singleTapConfirmedHelper(p: GeoPoint?): Boolean {
        InfoWindow.closeAllInfoWindowsOn(fragmentRouteMapBinding.routeMap)
        return true
    }

    override fun longPressHelper(p: GeoPoint?): Boolean {
        return false
    }
}