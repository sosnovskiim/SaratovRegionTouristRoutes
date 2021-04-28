package com.sosnowskydevelop.tourroutessaratovregion.data

class RouteRepository {
    /*
    TODO Sample
     */
    private val routes: Array<Route> = arrayOf(
        Route(
            _id = 1, _regionId = 1,
            _name = "Ровенский-1",
        ),
        Route(
            _id = 2, _regionId = 1,
            _name = "Ровенский-2",
        ),
        Route(
            _id = 3, _regionId = 2,
            _name = "Ровенский-3",
        ),
    )

    fun getRoutes(regionId: Long): Array<Route> {
        var result: Array<Route> = arrayOf()
        routes.forEach { route ->
            if (route.regionId == regionId) {
                result += route
            }
        }
        return result
    }

    fun getRoute(routeId: Long): Route = routes[routeId.toInt() - 1]

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: RouteRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: RouteRepository().also { instance = it }
            }
    }
}