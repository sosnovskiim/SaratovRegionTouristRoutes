package com.sosnowskydevelop.tourroutessaratovregion.fragments

import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.sosnowskydevelop.tourroutessaratovregion.data.RoutePoint
import com.sosnowskydevelop.tourroutessaratovregion.data.RouteType
import com.sosnowskydevelop.tourroutessaratovregion.databinding.FragmentRouteDetailBinding
import com.sosnowskydevelop.tourroutessaratovregion.utilities.BUNDLE_KEY_ROUTE_ID_ROUTE_LIST_TO_ROUTE_DETAIL
import com.sosnowskydevelop.tourroutessaratovregion.utilities.InjectorUtils
import com.sosnowskydevelop.tourroutessaratovregion.utilities.REQUEST_KEY_ROUTE_ID_ROUTE_LIST_TO_ROUTE_DETAIL
import com.sosnowskydevelop.tourroutessaratovregion.viewmodels.RouteDetailViewModel
import org.osmdroid.api.IMapController
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Polyline

class RouteDetailFragment : Fragment() {
    private lateinit var fragmentRouteDetailBinding: FragmentRouteDetailBinding

    private val routeDetailViewModel: RouteDetailViewModel by viewModels {
        InjectorUtils.provideRouteDetailViewModelFactory()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        fragmentRouteDetailBinding =
                FragmentRouteDetailBinding.inflate(inflater, container, false)
        return fragmentRouteDetailBinding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentRouteDetailBinding.viewModel = routeDetailViewModel

        setFragmentResultListener(
                requestKey = REQUEST_KEY_ROUTE_ID_ROUTE_LIST_TO_ROUTE_DETAIL
        ) { _, bundle ->
            val routeId: Long = bundle.getLong(BUNDLE_KEY_ROUTE_ID_ROUTE_LIST_TO_ROUTE_DETAIL)
            routeDetailViewModel.initDetails(routeId = routeId)
            initMap()
        }
    }

    private lateinit var mapController: IMapController
    private val geoPoints: MutableList<GeoPoint> = mutableListOf()

    @RequiresApi(Build.VERSION_CODES.N)
    private fun initMap() {
        initPreferences()

        fragmentRouteDetailBinding.routeMap.setTileSource(TileSourceFactory.MAPNIK)
        fragmentRouteDetailBinding.routeMap.setBuiltInZoomControls(true)
        fragmentRouteDetailBinding.routeMap.setMultiTouchControls(true)

        mapController = fragmentRouteDetailBinding.routeMap.controller
        mapController.setZoom(12.0)

        val routeStartPoint: RoutePoint = routeDetailViewModel.routeStartPoint
        addMarkerToMap(
            isStartPoint = true,
            routeGeoPoint = GeoPoint(routeStartPoint.latitude, routeStartPoint.longitude),
            routePointName = routeStartPoint.name,
        )

        val routeIntermediatePoints: Array<RoutePoint>? =
            routeDetailViewModel.routeIntermediatePoints
        routeIntermediatePoints?.forEach { routePoint ->
            addMarkerToMap(
                routeGeoPoint = GeoPoint(routePoint.latitude, routePoint.longitude),
                routePointName = routePoint.name,
            )
        }

        val routeEndPoint: RoutePoint? = routeDetailViewModel.routeEndPoint
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
        val marker = Marker(fragmentRouteDetailBinding.routeMap)
        marker.position = routeGeoPoint
        marker.title = routePointName
        /*TODO Experiment with Marker.setAnchor().*/
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
        fragmentRouteDetailBinding.routeMap.overlays.add(marker)
        fragmentRouteDetailBinding.routeMap.invalidate()
        if (isStartPoint) mapController.setCenter(routeGeoPoint)
    }

    override fun onResume() {
        super.onResume()

        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        fragmentRouteDetailBinding.routeMap.onResume() //needed for compass, my location overlays, v6.0.0 and up
    }

    override fun onPause() {
        super.onPause()

        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().save(this, prefs);

        fragmentRouteDetailBinding.routeMap.onPause() //needed for compass, my location overlays, v6.0.0 and up
    }
}