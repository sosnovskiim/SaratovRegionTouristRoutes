package com.sosnowskydevelop.tourroutessaratovregion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.sosnowskydevelop.tourroutessaratovregion.databinding.FragmentRouteDetailBinding
import com.sosnowskydevelop.tourroutessaratovregion.utilities.BUNDLE_KEY_ROUTE_ID_ROUTE_LIST_TO_ROUTE_DETAIL
import com.sosnowskydevelop.tourroutessaratovregion.utilities.InjectorUtils
import com.sosnowskydevelop.tourroutessaratovregion.utilities.REQUEST_KEY_ROUTE_ID_ROUTE_LIST_TO_ROUTE_DETAIL
import com.sosnowskydevelop.tourroutessaratovregion.viewmodels.RouteDetailViewModel

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
}