package com.sosnowskydevelop.tourroutessaratovregion.fragments

import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.sosnowskydevelop.tourroutessaratovregion.data.RoutePoint
import com.sosnowskydevelop.tourroutessaratovregion.databinding.FragmentRouteMapBinding
import com.sosnowskydevelop.tourroutessaratovregion.utilities.*
import com.sosnowskydevelop.tourroutessaratovregion.viewmodels.RouteMapViewModel
import org.osmdroid.api.IMapController
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker

class RouteMapFragment : Fragment() {
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

        fragmentRouteMapBinding.routeMap.setTileSource(TileSourceFactory.MAPNIK)
        fragmentRouteMapBinding.routeMap.setBuiltInZoomControls(true)
        fragmentRouteMapBinding.routeMap.setMultiTouchControls(true)

        mapController = fragmentRouteMapBinding.routeMap.controller
        mapController.setZoom(12.0)

        val routeStartPoint: RoutePoint = routeMapViewModel.routeStartPoint
        addMarkerToMap(
            isStartPoint = true,
            routeGeoPoint = GeoPoint(routeStartPoint.latitude, routeStartPoint.longitude),
            routePointName = routeStartPoint.name,
        )

        val routeIntermediatePoints: Array<RoutePoint>? =
            routeMapViewModel.routeIntermediatePoints
        routeIntermediatePoints?.forEach { routePoint ->
            addMarkerToMap(
                routeGeoPoint = GeoPoint(routePoint.latitude, routePoint.longitude),
                routePointName = routePoint.name,
            )
        }

        val routeEndPoint: RoutePoint? = routeMapViewModel.routeEndPoint
        if (routeEndPoint != null) {
            addMarkerToMap(
                routeGeoPoint = GeoPoint(routeEndPoint.latitude, routeEndPoint.longitude),
                routePointName = routeEndPoint.name,
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
        routeGeoPoint: GeoPoint,
        routePointName: String?,
    ) {
        geoPoints.add(routeGeoPoint)
        val marker = Marker(fragmentRouteMapBinding.routeMap)
        marker.position = routeGeoPoint
        marker.title = routePointName
        /*TODO Experiment with Marker.setAnchor().*/
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
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
}