package com.example.streamapp.bancoDados

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DataBaseHelper(context: Context): SQLiteOpenHelper(context, "bancoStream.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val sql = ""

        try {
            db?.execSQL( sql )
            Log.i("info_db", "Tabelas Criadas com Sucesso!")
        }catch (e: Exception){
            e.printStackTrace()
            Log.i("info_db", "Erro na Criação das tabelas")
        }


        TODO("Not yet implemented")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }


}