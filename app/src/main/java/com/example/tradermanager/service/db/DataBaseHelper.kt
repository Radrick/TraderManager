package com.example.tradermanager.service.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.tradermanager.service.constants.*

class DataBaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    companion object{

        const val VERSION = 1
        const val DATABASE_NAME = "Trader_manager.db"
        const val CREATE_TABLE = ("create table"+ OperationDatabaseConstants.TABLE_NAME + " ( "
                + OperationDatabaseConstants.OPERATION.COLUMNS.ID + " integer primary key autoincrement, "
                + OperationDatabaseConstants.OPERATION.COLUMNS.INPUT_POINT + " integer, "
                + OperationDatabaseConstants.OPERATION.COLUMNS.OUTPUT_POINT + " integer, "
                + OperationDatabaseConstants.OPERATION.COLUMNS.QUANTITY_CONTRACT + " integer, "
                + OperationDatabaseConstants.OPERATION.COLUMNS.RESULT_POINT + " integer, "
                + OperationDatabaseConstants.OPERATION.COLUMNS.RESULT_FINANCE + " float ); ")

    }

}