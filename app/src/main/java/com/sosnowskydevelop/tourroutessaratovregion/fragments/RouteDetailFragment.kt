package com.sosnowskydevelop.tourroutessaratovregion.fragments

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sosnowskydevelop.tourroutessaratovregion.R
import com.sosnowskydevelop.tourroutessaratovregion.databinding.FragmentRouteDetailBinding
import com.sosnowskydevelop.tourroutessaratovregion.utilities.*
import com.sosnowskydevelop.tourroutessaratovregion.viewmodels.RouteDetailViewModel

class RouteDetailFragment : Fragment() {
    private lateinit var fragmentRouteDetailBinding: FragmentRouteDetailBinding

    private val routeDetailViewModel: RouteDetailViewModel by viewModels {
        InjectorUtils.provideRouteDetailViewModelFactory()
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
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_map, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_map -> {
                setFragmentResult(
                    requestKey = REQUEST_KEY_ROUTE_ID_ROUTE_DETAIL_TO_ROUTE_MAP,
                    result = bundleOf(
                        BUNDLE_KEY_ROUTE_ID_ROUTE_DETAIL_TO_ROUTE_MAP
                                to routeDetailViewModel.routeId
                    )
                )
                findNavController().navigate(R.id.action_routeDetailFragment_to_routeMapFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}