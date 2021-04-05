package com.sosnowskydevelop.tourroutessaratovregion.data

class RouteRepository {
    fun getRoutes(regionId: Long): Array<Route> {
        return arrayOf(
                Route(
                        _id = 1, _regionId = 22,
                        _authors = arrayOf(
                                "Чижикова Валентина",
                        ),
                        _school = "МАОУ \"ФТЛ №1\" г.Саратова",
                        _leaders = arrayOf(
                                "Сосновская Регина Леонидовна, учитель биологии"
                        ),
                        _name = "Новоузенский район - 1",
                        _type = RouteType.LINEAR,
                        _startPoint = RoutePoint(
                                _id = 1,
                                _routeId = 1,
                                _latitude = 50.459035,
                                _longitude = 48.135482,
                                _name = "Администрация, г. Новоузенск",
                        ),
                        _endPoint = RoutePoint(
                                _id = 2,
                                _routeId = 1,
                                _latitude = 50.46330034264079,
                                _longitude = 48.13344970101825,
                                _name = "Школа №8, г.Новоузенск",
                        ),
                        _intermediatePoints = arrayOf(
                                RoutePoint(
                                        _id = 3,
                                        _routeId = 1,
                                        _latitude = 50.460852,
                                        _longitude = 48.138621,
                                        _name = "Краеведческий музей, г.Новоузенск",
                                ),
                        ),
                ),
                Route(
                        _id = 2, _regionId = 22,
                        _authors = arrayOf(
                                "Чижикова Валентина",
                        ),
                        _school = "МАОУ \"ФТЛ №1\" г.Саратова",
                        _name = "Новоузенский район - 2",
                        _type = RouteType.LINEAR,
                        _startPoint = RoutePoint(
                                _id = 4,
                                _routeId = 2,
                                _latitude = 50.47716416155355,
                                _longitude = 48.10918588687836,
                                _name = "г.Новоузенск",
                        ),
                ),
                Route(
                        _id = 3, _regionId = 28,
                        _authors = arrayOf(
                                "Чижикова Валентина, 9 класс",
                        ),
                        _school = "МАОУ \"ФТЛ №1\" г.Саратова",
                        _name = "Ровенский-1",
                        _type = RouteType.LINEAR,
                        _startPoint = RoutePoint(
                                _id = 5,
                                _routeId = 3,
                                _latitude = 50.780491254003636,
                                _longitude = 46.04220178099646,
                                _name = "Краеведческий музей, село Ровное, Саратовская область",
                        ),
                        _endPoint = RoutePoint(
                                _id = 6,
                                _routeId = 3,
                                _latitude = 50.78394133131223,
                                _longitude = 46.06115202693455,
                                _name = "Виндсерф на реке Волга в селе Ровное",
                        ),
                        _intermediatePoints = arrayOf(
                                RoutePoint(
                                        _id = 7,
                                        _routeId = 3,
                                        _latitude = 50.78039092360099,
                                        _longitude = 46.042499506198325,
                                        _name = "Валун, село Ровное",
                                ),
                                RoutePoint(
                                        _id = 8,
                                        _routeId = 3,
                                        _latitude = 50.781157849662215,
                                        _longitude = 46.045361423217216,
                                        _name = "Администрация, село Ровное",
                                ),
                        ),
                ),
                Route(
                        _id = 4, _regionId = 28,
                        _authors = arrayOf(
                                "Чижикова Валентина, 9 класс",
                        ),
                        _school = "МАОУ \"ФТЛ №1\" г.Саратова",
                        _name = "Ровенский-2",
                        _type = RouteType.NONLINEAR,
                        _startPoint = RoutePoint(
                                _id = 9,
                                _routeId = 4,
                                _latitude = 50.77092666382615,
                                _longitude = 46.08409378913102,
                                _name = "село Ровное",
                        ),
                        _intermediatePoints = arrayOf(
                                RoutePoint(
                                        _id = 10,
                                        _routeId = 4,
                                        _latitude = 50.695765,
                                        _longitude = 46.077969,
                                        _name = "поселок Лиманный",
                                ),
                                RoutePoint(
                                        _id = 11,
                                        _routeId = 4,
                                        _latitude = 50.779357,
                                        _longitude = 46.414702,
                                        _name = "село Песчаное",
                                ),
                                RoutePoint(
                                        _id = 12,
                                        _routeId = 4,
                                        _latitude = 50.864369,
                                        _longitude = 46.492568,
                                        _name = "Администрация, село Кривояр",
                                ),
                        ),
                ),
        )
    }

    companion object {
        // For Singleton instantiation
        @Volatile private var instance: RouteRepository? = null

        fun getInstance() =
                instance ?: synchronized(this) {
                    instance ?: RouteRepository().also { instance = it }
                }
    }
}