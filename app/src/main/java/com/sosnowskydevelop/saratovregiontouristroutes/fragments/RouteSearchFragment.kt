package com.sosnowskydevelop.saratovregiontouristroutes.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sosnowskydevelop.saratovregiontouristroutes.adapters.RouteAdapter
import com.sosnowskydevelop.saratovregiontouristroutes.databinding.FragmentRouteSearchBinding
import com.sosnowskydevelop.saratovregiontouristroutes.utilities.*
import com.sosnowskydevelop.saratovregiontouristroutes.viewmodels.RouteSearchViewModel

class RouteSearchFragment : Fragment() {
    private lateinit var fragmentRouteSearchBinding: FragmentRouteSearchBinding

    private val routeSearchViewModel: RouteSearchViewModel by viewModels {
        InjectorUtils.provideRouteSearchViewModelFactory(context = requireContext())
    }

    private lateinit var routeListAdapter: RouteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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

        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Поиск маршрутов"

        val routeLayoutManager = LinearLayoutManager(requireContext())
        fragmentRouteSearchBinding.routeList.layoutManager = routeLayoutManager
        routeListAdapter = RouteAdapter(
            routes = arrayOf(),
            fragment = this,
        )
        fragmentRouteSearchBinding.routeList.adapter = routeListAdapter

        fragmentRouteSearchBinding.routeSearch.addTextChangedListener {
            searchRoutes(searchString = it.toString())
        }

        fragmentRouteSearchBinding.isSearchByRoutePoints.setOnCheckedChangeListener { _, _ ->
            searchRoutes(searchString = fragmentRouteSearchBinding.routeSearch.text.toString())
        }

        setFragmentResultListener(
            requestKey = REQUEST_KEY_REGION_ID_ROUTE_LIST_TO_ROUTE_SEARCH
        ) { _, bundle ->
            val regionId: Long = bundle.getLong(BUNDLE_KEY_REGION_ID_ROUTE_LIST_TO_ROUTE_SEARCH)
            routeSearchViewModel.regionId = regionId
        }
    }

    private fun searchRoutes(searchString: String) {
        if (searchString.isNotBlank()) {
            if (fragmentRouteSearchBinding.isSearchByRoutePoints.isChecked) {
                routeSearchViewModel.updateRoutes(
                    searchString = searchString,
                    isSearchByRoutePoints = true,
                )
            } else {
                routeSearchViewModel.updateRoutes(
                    searchString = searchString,
                    isSearchByRoutePoints = false,
                )
            }
            if (routeSearchViewModel.routes.isNotEmpty()) {
                fragmentRouteSearchBinding.nothingFound.visibility = View.INVISIBLE
                fragmentRouteSearchBinding.routeList.visibility = View.VISIBLE
                routeListAdapter.routes = routeSearchViewModel.routes
                routeListAdapter.notifyDataSetChanged()
            } else {
                fragmentRouteSearchBinding.routeList.visibility = View.INVISIBLE
                fragmentRouteSearchBinding.nothingFound.visibility = View.VISIBLE
            }
        } else {
            fragmentRouteSearchBinding.routeList.visibility = View.INVISIBLE
            fragmentRouteSearchBinding.nothingFound.visibility = View.VISIBLE
        }
    }
}