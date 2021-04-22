package com.sosnowskydevelop.tourroutessaratovregion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sosnowskydevelop.tourroutessaratovregion.adapters.RouteAdapter
import com.sosnowskydevelop.tourroutessaratovregion.databinding.FragmentRouteListBinding
import com.sosnowskydevelop.tourroutessaratovregion.utilities.BUNDLE_KEY_REGION_ID_REGION_LIST_TO_ROUTE_LIST
import com.sosnowskydevelop.tourroutessaratovregion.utilities.InjectorUtils
import com.sosnowskydevelop.tourroutessaratovregion.utilities.REQUEST_KEY_REGION_ID_REGION_LIST_TO_ROUTE_LIST
import com.sosnowskydevelop.tourroutessaratovregion.viewmodels.RouteListViewModel

class RouteListFragment : Fragment() {
    private lateinit var fragmentRouteListBinding: FragmentRouteListBinding

    private val routeListViewModel: RouteListViewModel by viewModels {
        InjectorUtils.provideRouteListViewModelFactory()
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
        }
    }
}