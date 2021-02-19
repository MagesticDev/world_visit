package com.example.worldvisit.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.worldvisit.ws.Pays
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class DatabaseHelper(context: Context?) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_pays)
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("DROP TABLE IF EXISTS '$TABLE_pays'")
        onCreate(db)
    }

    fun addpaysDetail(
        name: String?,
        capital: String?,
        region: String?,
        date: String?,
        short_code: String?
    ): Long {
        val db = this.writableDatabase
        // Creating content values
        val values = ContentValues()
        values.put(NAME, name)
        values.put(CAPITALE, capital)
        values.put(CONTINENT, region)
        values.put(DATE, date)
        values.put(SHORT_CODE, short_code)
        // insert row in pays table
        return db.insert(
            TABLE_pays,
            null,
            values
        )
    }// adding to pays list


    // looping through all rows and adding to list
    val allpaysList: ArrayList<Pays>
        get() {
           val selectQuery =
                "SELECT  * FROM $TABLE_pays"
            val db = this.readableDatabase
            val c = db.rawQuery(selectQuery, null)
            // looping through all rows and adding to list
            val pays = ArrayList<Pays>()

            if (c.moveToFirst()) {
                do {
                    pays.add(
                        Pays(
                            c.getString(c.getColumnIndex(NAME)),
                            c.getString(c.getColumnIndex(CAPITALE)),
                            c.getString(c.getColumnIndex(CONTINENT)),
                            c.getString(c.getColumnIndex(DATE)),
                            c.getString(c.getColumnIndex(SHORT_CODE)),
                            "http://www.geognos.com/api/en/countries/flag/" + c.getString(c.getColumnIndex(SHORT_CODE)).toString().toUpperCase() + ".png"
                        )
                    );
                } while (c.moveToNext())
                //Log.d("array", pays.toString())
            }
            return pays
        }

    companion object {
        var DATABASE_NAME = "pays_database"
        private const val DATABASE_VERSION = 1
        private const val TABLE_pays = "pays"
        private const val KEY_ID = "id"
        private const val NAME = "name"
        private const val CAPITALE = "capital"
        private const val CONTINENT = "region"
        private const val DATE = "date"
        private const val SHORT_CODE = "short_code"

        /*CREATE TABLE pays ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone_number TEXT......);*/
        private const val CREATE_TABLE_pays = ("CREATE TABLE "
                + TABLE_pays + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME + " varchard(50),"
                + CAPITALE + " varchard(50),"
                + CONTINENT + " varchard(50),"
                + DATE + " TEXT,"
                + SHORT_CODE + " varchard(50)"
                + ");")
    }

    init {
        Log.d(
            "table",
            CREATE_TABLE_pays
        )
    }
}