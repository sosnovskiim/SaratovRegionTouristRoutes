package com.sosnowskydevelop.saratovregiontouristroutes.data

import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import com.sosnowskydevelop.saratovregiontouristroutes.utilities.DatabaseHelper
import java.io.IOException

class RegionRepository(context: Context) {
    private var regions: Array<Region> = arrayOf()

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
            "Region",
            arrayOf("_id", "_name", "_fileName"),
            null, null, null, null, null
        )
        var isEntryNotEmpty: Boolean = cursor.moveToFirst()
        while (isEntryNotEmpty) {
            regions += Region(
                _id = cursor.getLong(cursor.getColumnIndex("_id")),
                _name = cursor.getString(cursor.getColumnIndex("_name")),
                _fileName = cursor.getString(cursor.getColumnIndex("_fileName")),
            )
            isEntryNotEmpty = cursor.moveToNext()
        }
        cursor.close()
    }

    fun getRegions(): Array<Region> = regions

    fun getRegion(regionId: Long) = regions[regionId.toInt() - 1]

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: RegionRepository? = null

        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: RegionRepository(context = context).also { instance = it }
            }
    }
}