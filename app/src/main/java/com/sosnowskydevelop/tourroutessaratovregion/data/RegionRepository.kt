package com.sosnowskydevelop.tourroutessaratovregion.data

import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import com.sosnowskydevelop.tourroutessaratovregion.utilities.DatabaseHelper
import java.io.IOException

class RegionRepository(context: Context) {
    private var regions: Array<Region> = arrayOf(
//        Region(_id = 1, _name = "Александрово-Гайский район"),
//        Region(_id = 2, _name = "Аркадакский район"),
//        Region(_id = 3, _name = "Аткарский район"),
//        Region(_id = 4, _name = "Базарно-Карабулакский район"),
//        Region(_id = 5, _name = "Балаковский район"),
//        Region(_id = 6, _name = "Балашовский район"),
//        Region(_id = 7, _name = "Балтайский район"),
//        Region(_id = 8, _name = "Вольский район"),
//        Region(_id = 9, _name = "Воскресенский район"),
//        Region(_id = 10, _name = "Дергачёвский район"),
//        Region(_id = 11, _name = "Духовницкий район"),
//        Region(_id = 12, _name = "Екатериновский район"),
//        Region(_id = 13, _name = "Ершовский район"),
//        Region(_id = 14, _name = "Ивантеевский район"),
//        Region(_id = 15, _name = "Калининский район"),
//        Region(_id = 16, _name = "Красноармейский район"),
//        Region(_id = 17, _name = "Краснокутский район"),
//        Region(_id = 18, _name = "Краснопартизанский район"),
//        Region(_id = 19, _name = "Лысогорский район"),
//        Region(_id = 20, _name = "Марксовский район"),
//        Region(_id = 21, _name = "Новобурасский район"),
//        Region(_id = 22, _name = "Новоузенский район"),
//        Region(_id = 23, _name = "Озинский район"),
//        Region(_id = 24, _name = "Перелюбский район"),
//        Region(_id = 25, _name = "Петровский район"),
//        Region(_id = 26, _name = "Питерский район"),
//        Region(_id = 27, _name = "Пугачёвский район"),
//        Region(_id = 28, _name = "Ровенский район"),
//        Region(_id = 29, _name = "Романовский район"),
//        Region(_id = 30, _name = "Ртищевский район"),
//        Region(_id = 31, _name = "Самойловский район"),
//        Region(_id = 32, _name = "Саратовский район"),
//        Region(_id = 33, _name = "Советский район"),
//        Region(_id = 34, _name = "Татищевский район"),
//        Region(_id = 35, _name = "Турковский район"),
//        Region(_id = 36, _name = "Фёдоровский район"),
//        Region(_id = 37, _name = "Хвалынский район"),
//        Region(_id = 38, _name = "Энгельсский район"),
    )

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