package com.sosnowskydevelop.saratovregiontouristroutes.data

import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import com.sosnowskydevelop.saratovregiontouristroutes.utilities.DatabaseHelper
import java.io.IOException

class RoutePointRepository(context: Context) {
//    /*
//    TODO Sample
//     */
//    private val routePoints: Array<RoutePoint> = arrayOf(
//        /*
//        TODO Start
//         */
//        RoutePoint(
//            _id = 1, _routeId = 1,
//            _name = "Краеведческий музей, село Ровное, Саратовская область",
//            _latitude = 50.780491254003636,
//            _longitude = 46.04220178099646,
//            _page = 1,
//        ),
//        /*
//        TODO End
//         */
//        RoutePoint(
//            _id = 2, _routeId = 1,
//            _name = "Виндсерф на реке Волга в селе Ровное",
//            _latitude = 50.78394133131223,
//            _longitude = 46.06115202693455,
//            _page = 7,
//        ),
//        /*
//        TODO Intermediate
//         */
//        RoutePoint(
//            _id = 3, _routeId = 1,
//            _name = "Валун, село Ровное",
//            _latitude = 50.78039092360099,
//            _longitude = 46.042499506198325,
//            _page = 2,
//        ),
//        RoutePoint(
//            _id = 4, _routeId = 1,
//            _name = "Администрация, село Ровное",
//            _latitude = 50.781157849662215,
//            _longitude = 46.045361423217216,
//            _page = 3,
//        ),
//        /*
//        TODO Start
//         */
//        RoutePoint(
//            _id = 5, _routeId = 2,
//            _name = "село Ровное",
//            _latitude = 50.77092666382615,
//            _longitude = 46.08409378913102,
//            _page = 1,
//        ),
//        /*
//        TODO End
//         */
//        RoutePoint(
//            _id = 6, _routeId = 2,
//            _name = "Кирха, село Привольное",
//            _latitude = 50.9259050846799,
//            _longitude = 46.0751811388395,
//            _page = 2,
//        ),
//        /*
//        TODO Intermediate
//         */
//        RoutePoint(
//            _id = 7, _routeId = 2,
//            _name = "поселок Лиманный",
//            _latitude = 50.695765,
//            _longitude = 46.077969,
//            _page = 3,
//        ),
//        /*
//        TODO Start
//         */
//        RoutePoint(
//            _id = 8, _routeId = 3,
//            _name = "посёлок городского типа Приволжский",
//            _latitude = 51.0879534389997,
//            _longitude = 45.9510203578405,
//            _page = 1,
//        ),
//    )

    private var routePoints: Array<RoutePoint> = arrayOf()

    init {
        val databaseHelper = DatabaseHelper(context)
        val database: SQLiteDatabase

        try {
            databaseHelper.updateDataBase()
        } catch (mIOException: IOException) {
            throw Error("UnableToUpdateDatabase")
        }

        try {
            database = databaseHelper.readableDatabase
        } catch (mSQLException: SQLException) {
            throw mSQLException
        }

        val cursor: Cursor = database.query(
            "RoutePoint",
            arrayOf("_id", "_routeId", "_name", "_latitude", "_longitude"),
            null, null, null, null, null
        )
        var isEntryNotEmpty: Boolean = cursor.moveToFirst()
        while (isEntryNotEmpty) {
            routePoints += RoutePoint(
                _id = cursor.getLong(cursor.getColumnIndex("_id")),
                _routeId = cursor.getLong(cursor.getColumnIndex("_routeId")),
                _name = cursor.getString(cursor.getColumnIndex("_name")),
                _latitude = cursor.getDouble(cursor.getColumnIndex("_latitude")),
                _longitude = cursor.getDouble(cursor.getColumnIndex("_longitude")),
            )
            isEntryNotEmpty = cursor.moveToNext()
        }
        cursor.close()
    }

    fun getRoutePoints(routeId: Long): Array<RoutePoint> {
        var result: Array<RoutePoint> = arrayOf()
        routePoints.forEach { routePoint ->
            if (routePoint.routeId == routeId) {
                result += routePoint
            } else if (result.isNotEmpty()) {
                return result
            }
        }
        return result
    }

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: RoutePointRepository? = null

        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: RoutePointRepository(context = context).also { instance = it }
            }
    }
}