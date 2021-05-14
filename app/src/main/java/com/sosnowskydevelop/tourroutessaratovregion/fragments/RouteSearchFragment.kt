package com.sosnowskydevelop.tourroutessaratovregion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sosnowskydevelop.tourroutessaratovregion.adapters.RouteAdapter
import com.sosnowskydevelop.tourroutessaratovregion.databinding.FragmentRouteSearchBinding
import com.sosnowskydevelop.tourroutessaratovregion.utilities.InjectorUtils
import com.sosnowskydevelop.tourroutessaratovregion.viewmodels.RouteSearchViewModel


class RouteSearchFragment : Fragment() {
    private lateinit var fragmentRouteSearchBinding: FragmentRouteSearchBinding

    private val routeSearchViewModel: RouteSearchViewModel by viewModels {
        InjectorUtils.provideRouteSearchViewModelFactory(context = requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentRouteSearchBinding =
            FragmentRouteSearchBinding.inflate(inflater, container, false)
        return fragmentRouteSearchBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar?.title =
            "Поиск маршрутов по области"

        val linearLayoutManager = LinearLayoutManager(requireContext())
        fragmentRouteSearchBinding.routeList.layoutManager = linearLayoutManager

        val routeListAdapter = RouteAdapter(
            routes = routeSearchViewModel.routes,
            fragment = this,
        )
        fragmentRouteSearchBinding.routeList.adapter = routeListAdapter
        routeListAdapter.routes = routeSearchViewModel.routes

        fragmentRouteSearchBinding.routeSearch.addTextChangedListener {
            routeSearchViewModel.searchQuery = it.toString()
            routeSearchViewModel.notifySearchQueryChanged()
            routeListAdapter.routes = routeSearchViewModel.routes
            routeListAdapter.notifyDataSetChanged()
        }
    }
}