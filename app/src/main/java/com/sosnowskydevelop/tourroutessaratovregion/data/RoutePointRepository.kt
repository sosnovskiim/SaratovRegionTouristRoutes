package com.sosnowskydevelop.tourroutessaratovregion.data

class RoutePointRepository {
    /*
    TODO Sample
     */
    private val routePoints: Array<RoutePoint> = arrayOf(
        /*
        TODO Start
         */
        RoutePoint(
            _id = 1, _routeId = 1,
            _name = "Краеведческий музей, село Ровное, Саратовская область",
            _latitude = 50.780491254003636,
            _longitude = 46.04220178099646,
            _page = 1,
        ),
        /*
        TODO End
         */
        RoutePoint(
            _id = 2, _routeId = 1,
            _name = "Виндсерф на реке Волга в селе Ровное",
            _latitude = 50.78394133131223,
            _longitude = 46.06115202693455,
            _page = 7,
        ),
        /*
        TODO Intermediate
         */
        RoutePoint(
            _id = 3, _routeId = 1,
            _name = "Валун, село Ровное",
            _latitude = 50.78039092360099,
            _longitude = 46.042499506198325,
            _page = 2,
        ),
        RoutePoint(
            _id = 4, _routeId = 1,
            _name = "Администрация, село Ровное",
            _latitude = 50.781157849662215,
            _longitude = 46.045361423217216,
            _page = 3,
        ),
        /*
        TODO Start
         */
        RoutePoint(
            _id = 5, _routeId = 2,
            _name = "село Ровное",
            _latitude = 50.77092666382615,
            _longitude = 46.08409378913102,
            _page = 1,
        ),
        /*
        TODO End
         */
        RoutePoint(
            _id = 6, _routeId = 2,
            _name = "Кирха, село Привольное",
            _latitude = 50.9259050846799,
            _longitude = 46.0751811388395,
            _page = 2,
        ),
        /*
        TODO Intermediate
         */
        RoutePoint(
            _id = 7, _routeId = 2,
            _name = "поселок Лиманный",
            _latitude = 50.695765,
            _longitude = 46.077969,
            _page = 3,
        ),
        /*
        TODO Start
         */
        RoutePoint(
            _id = 8, _routeId = 3,
            _name = "посёлок городского типа Приволжский",
            _latitude = 51.0879534389997,
            _longitude = 45.9510203578405,
            _page = 1,
        ),
    )

    fun getStartPoint(routeId: Long): RoutePoint {
        routePoints.forEach { routePoint ->
            when {
                routePoint.id == 1L && routeId == 1L -> {
                    return routePoint
                }
                routePoint.id == 5L && routeId == 2L -> {
                    return routePoint
                }
                routePoint.id == 8L && routeId == 3L -> {
                    return routePoint
                }
            }
        }
        /*
        TODO Plug
         */
        return routePoints[0]
    }

    fun getEndPoint(routeId: Long): RoutePoint? {
        routePoints.forEach { routePoint ->
            when {
                routePoint.id == 2L && routeId == 1L -> {
                    return routePoint
                }
                routePoint.id == 6L && routeId == 2L -> {
                    return routePoint
                }
            }
        }
        return null
    }

    fun getIntermediatePoints(routeId: Long): Array<RoutePoint>? {
        var result: Array<RoutePoint> = arrayOf()
        routePoints.forEach { routePoint ->
            when {
                routePoint.id == 3L && routeId == 1L -> result += routePoint
                routePoint.id == 4L && routeId == 1L -> result += routePoint
                routePoint.id == 7L && routeId == 2L -> result += routePoint
            }
        }
        return if (result.isNotEmpty()) result else null
    }

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: RoutePointRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: RoutePointRepository().also { instance = it }
            }
    }
}