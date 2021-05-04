package com.sosnowskydevelop.tourroutessaratovregion.data

import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import com.sosnowskydevelop.tourroutessaratovregion.utilities.DatabaseHelper
import java.io.IOException

class RouteRepository(context: Context) {
    private var routes: Array<Route> = arrayOf()

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
            "Route",
            arrayOf("_id", "_regionId", "_name", "_fileName"),
            null, null, null, null, null
        )
        var isEntryNotEmpty: Boolean = cursor.moveToFirst()
        while (isEntryNotEmpty) {
            routes += Route(
                _id = cursor.getLong(cursor.getColumnIndex("_id")),
                _regionId = cursor.getLong(cursor.getColumnIndex("_regionId")),
                _name = cursor.getString(cursor.getColumnIndex("_name")),
                _fileName = cursor.getString(cursor.getColumnIndex("_fileName")),
            )
            isEntryNotEmpty = cursor.moveToNext()
        }
        cursor.close()
    }

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

        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: RouteRepository(context = context).also { instance = it }
            }
    }
}