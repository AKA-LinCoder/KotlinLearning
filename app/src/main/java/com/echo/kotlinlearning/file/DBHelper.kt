package com.echo.kotlinlearning.file

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(val context: Context,val name: String,val version: Int): SQLiteOpenHelper(context,name,null,version){

    private val createBook = "create table Book(" +
            "id integer primary key autoincrement," +
            "author text," +
            "price real," +
            "pages integer," +
            "name text)"


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createBook)
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        TODO("Not yet implemented")
    }
}