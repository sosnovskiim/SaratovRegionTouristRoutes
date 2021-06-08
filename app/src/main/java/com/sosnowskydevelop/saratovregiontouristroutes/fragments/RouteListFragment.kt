package com.sosnowskydevelop.saratovregiontouristroutes.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sosnowskydevelop.saratovregiontouristroutes.R
import com.sosnowskydevelop.saratovregiontouristroutes.adapters.RouteAdapter
import com.sosnowskydevelop.saratovregiontouristroutes.databinding.FragmentRouteListBinding
import com.sosnowskydevelop.saratovregiontouristroutes.utilities.*
import com.sosnowskydevelop.saratovregiontouristroutes.viewmodels.RouteListViewModel

class RouteListFragment : Fragment() {
    private lateinit var fragmentRouteListBinding: FragmentRouteListBinding

    private val routeListViewModel: RouteListViewModel by viewModels {
        InjectorUtils.provideRouteListViewModelFactory(context = requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_route_search, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                setFragmentResult(
                    requestKey = REQUEST_KEY_REGION_ID_ROUTE_LIST_TO_ROUTE_SEARCH,
                    result = bundleOf(
                        BUNDLE_KEY_REGION_ID_ROUTE_LIST_TO_ROUTE_SEARCH
                                to routeListViewModel.regionId
                    )
                )
                findNavController().navigate(R.id.action_routeListFragment_to_routeSearchFragment)
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
        fragmentRouteListBinding =
            FragmentRouteListBinding.inflate(inflater, container, false)
        return fragmentRouteListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val linearLayoutManager = LinearLayoutManager(requireContext())
        fragmentRouteListBinding.routeList.layoutManager = linearLayoutManager

        val routeListAdapter = RouteAdapter(
            routes = routeListViewModel.routes,
            fragment = this,
        )
        fragmentRouteListBinding.routeList.adapter = routeListAdapter

        setFragmentResultListener(
            requestKey = REQUEST_KEY_REGION_ID_REGION_LIST_TO_ROUTE_LIST
        ) { _, bundle ->
            val regionId: Long = bundle.getLong(BUNDLE_KEY_REGION_ID_REGION_LIST_TO_ROUTE_LIST)
            routeListViewModel.initRoutes(regionId = regionId)
            routeListAdapter.routes = routeListViewModel.routes
            subscribeUI()
        }

        if (routeListViewModel.regionName != null) subscribeRegionName()
        if (routeListViewModel.regionFileName != null) subscribeRegionImages()
    }

    private fun subscribeUI() {
        subscribeRegionName()
        subscribeRegionImages()
    }

    private fun subscribeRegionName() {
        (requireActivity() as AppCompatActivity).supportActionBar?.title =
            routeListViewModel.regionName
    }

    private fun subscribeRegionImages() {
        fragmentRouteListBinding.regionRatioMap.setImageResource(
            resources.getIdentifier(
                "region_ratio_map_${routeListViewModel.regionFileName}",
                "drawable",
                "com.sosnowskydevelop.saratovregiontouristroutes"
            )
        )
        fragmentRouteListBinding.regionEmblem.setImageResource(
            resources.getIdentifier(
                "region_emblem_${routeListViewModel.regionFileName}",
                "drawable",
                "com.sosnowskydevelop.saratovregiontouristroutes"
            )
        )
        fragmentRouteListBinding.regionMap.setImageResource(
            resources.getIdentifier(
                "region_map_${routeListViewModel.regionFileName}",
                "drawable",
                "com.sosnowskydevelop.saratovregiontouristroutes"
            )
        )
    }
}