package com.sosnowskydevelop.tourroutessaratovregion.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sosnowskydevelop.tourroutessaratovregion.adapters.RouteAdapter
import com.sosnowskydevelop.tourroutessaratovregion.databinding.FragmentRouteSearchBinding
import com.sosnowskydevelop.tourroutessaratovregion.utilities.*
import com.sosnowskydevelop.tourroutessaratovregion.viewmodels.RouteSearchViewModel

class RouteSearchFragment : Fragment() {
    private lateinit var fragmentRouteSearchBinding: FragmentRouteSearchBinding

    private val routeSearchViewModel: RouteSearchViewModel by viewModels {
        InjectorUtils.provideRouteSearchViewModelFactory(context = requireContext())
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
        fragmentRouteSearchBinding =
            FragmentRouteSearchBinding.inflate(inflater, container, false)
        return fragmentRouteSearchBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Поиск маршрутов"

        val routeLayoutManager = LinearLayoutManager(requireContext())
        fragmentRouteSearchBinding.routeList.layoutManager = routeLayoutManager
        val routeListAdapter = RouteAdapter(
            routes = arrayOf(),
            fragment = this,
        )
        fragmentRouteSearchBinding.routeList.adapter = routeListAdapter

        fragmentRouteSearchBinding.routeSearch.addTextChangedListener {
            val searchString: String = it.toString()
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

        setFragmentResultListener(
            requestKey = REQUEST_KEY_REGION_ID_ROUTE_LIST_TO_ROUTE_SEARCH
        ) { _, bundle ->
            val regionId: Long = bundle.getLong(BUNDLE_KEY_REGION_ID_ROUTE_LIST_TO_ROUTE_SEARCH)
            routeSearchViewModel.regionId = regionId
        }
    }
}