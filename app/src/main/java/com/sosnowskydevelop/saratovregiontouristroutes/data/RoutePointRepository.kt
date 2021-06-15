package com.sosnowskydevelop.saratovregiontouristroutes.data

import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import com.sosnowskydevelop.saratovregiontouristroutes.utilities.DatabaseHelper
import java.io.IOException

class RoutePointRepository(context: Context) {
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