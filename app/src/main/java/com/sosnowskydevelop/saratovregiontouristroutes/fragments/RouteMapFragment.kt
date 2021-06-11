package com.sosnowskydevelop.saratovregiontouristroutes.fragments

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.sosnowskydevelop.saratovregiontouristroutes.R
import com.sosnowskydevelop.saratovregiontouristroutes.adapters.MapMarkerInfoWindow
import com.sosnowskydevelop.saratovregiontouristroutes.data.RoutePoint
import com.sosnowskydevelop.saratovregiontouristroutes.databinding.FragmentRouteMapBinding
import com.sosnowskydevelop.saratovregiontouristroutes.utilities.BUNDLE_KEY_ROUTE_ID_ROUTE_DETAIL_TO_ROUTE_MAP
import com.sosnowskydevelop.saratovregiontouristroutes.utilities.InjectorUtils
import com.sosnowskydevelop.saratovregiontouristroutes.utilities.REQUEST_KEY_ROUTE_ID_ROUTE_DETAIL_TO_ROUTE_MAP
import com.sosnowskydevelop.saratovregiontouristroutes.viewmodels.RouteMapViewModel
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
        InjectorUtils.provideRouteMapViewModelFactory(context = requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_route_export, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_export -> {
                var stringUri = "yandexnavi://build_route_on_map?"
                if (routeMapViewModel.routeEndPoint != null) {
                    stringUri += "lat_to=${routeMapViewModel.routeEndPoint!!.latitude}" +
                            "&lon_to=${routeMapViewModel.routeEndPoint!!.longitude}"
                    if (routeMapViewModel.routeIntermediatePoints != null) {
                        routeMapViewModel.routeIntermediatePoints!!
                            .forEachIndexed { index, routePoint ->
                                stringUri += "&lat_via_${index}=${routePoint.latitude}" +
                                        "&lon_via_${index}=${routePoint.longitude}"
                            }
                    }
                } else {
                    stringUri += "lat_to=${routeMapViewModel.routeStartPoint.latitude}" +
                            "&lon_to=${routeMapViewModel.routeStartPoint.longitude}"
                }
                val uri: Uri = Uri.parse(stringUri)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                intent.setPackage("ru.yandex.yandexnavi")
                try {
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(
                        requireContext(),
                        "Приложение Яндекс.Навигатор не найдено",
                        Toast.LENGTH_LONG
                    ).show()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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
        mapController.setZoom(10.0)

        addMarkerToMap(
            isStartPoint = true,
            routePoint = routeMapViewModel.routeStartPoint,
        )

        if (routeMapViewModel.routeEndPoint != null) {
            addMarkerToMap(
                routePoint = routeMapViewModel.routeEndPoint!!,
            )
        }

        routeMapViewModel.routeIntermediatePoints?.forEach { routePoint ->
            addMarkerToMap(
                routePoint = routePoint,
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