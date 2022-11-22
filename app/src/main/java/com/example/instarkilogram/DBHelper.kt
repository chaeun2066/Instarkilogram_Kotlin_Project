package com.example.instarkilogram

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBHelper(val context: Context?, val name: String?, val factory: SQLiteDatabase.CursorFactory?, val version: Int):
    SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL(
                "create table userTBL(" +
                        "id text primary key," +
                        "name text not null," +
                        "password text not null," +
                        "phone text not null," +
                        "email text not null);"
            )
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists userTBL")
        onCreate(db)
    }

    fun insert(id: String, name: String, password: String, phone: String, email: String): Boolean {
        val db: SQLiteDatabase = writableDatabase
        var flag = false
        try {
            db.execSQL("insert into userTBL values('${id}', '${name}', '${password}', '${phone}', '${email}');")
            Log.d("instarkilogram", "insert ${id}  ${name} 성공")
            flag = true
        } catch (e: java.lang.Exception) {
            Log.d("instarkilogram", "insert ${id}  ${name} 실패")
            flag = false
        }

        return flag
    }

    fun selectCheckId(id: String): Boolean {
        val db: SQLiteDatabase = readableDatabase
        var cursor: Cursor? = null
        var flag = false

        try {
            cursor = db.rawQuery("select id from userTBL where id = '${id}'", null)
            if (cursor.moveToFirst()) {
                if (cursor.getString(0).equals(id)) {
                    flag = true
                }
                Log.d("instarkilogram", "selectCheckId ${id} 성공")
            }
        } catch (e: java.lang.Exception) {
            Log.d("instarkilogram", "selectCheckId ${id} 실패  ${e.printStackTrace()}")
            flag = false
        }
        return flag
    }

    fun selectLogin(id: String, password: String): Boolean {
        val db: SQLiteDatabase = readableDatabase
        var cursor: Cursor? = null
        var flag = false

        try {
            cursor = db.rawQuery(
                "select id, password from userTBL where id = '${id}' and password = '${password}'",
                null
            )
            if (cursor.moveToFirst()) {
                if (cursor.getString(0).equals(id) && cursor.getString(1).equals(password)) {
                    flag = true
                }
                Log.d("instarkilogram", "selectLogin ${id} ${name} 성공")
            }
        } catch (e: java.lang.Exception) {
            Log.d("instarkilogram", "selectLogin ${id} 실패")
            flag = false
        }
        return flag
    }

    fun selectAll(): MutableList<userTBL> {
        val db: SQLiteDatabase = readableDatabase
        var cursor: Cursor? = null
        val userList: MutableList<userTBL> = mutableListOf<userTBL>()

        try {
            cursor = db.rawQuery("select * from userTBL", null)
            if (cursor != null) {
                Log.d("instarkilogram", "selectAll 성공")
                while (cursor.moveToNext()) {
                    val userTBL = userTBL(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                    )
                    userList.add(userTBL)
                    Log.d("instarkilogram", "${cursor.getString(0)}")
                }
            }
        } catch (e: java.lang.Exception) {
            Log.d("instarkilogram", "selectAll 실패")
        }
        return userList
    }

    fun selectId(id: String): userTBL?{
        val db: SQLiteDatabase = readableDatabase
        var cursor: Cursor? = null
        var userTBL: userTBL? = null

        try{
            cursor = db.rawQuery("select * from userTBL where id = '${id}'", null)
            if(cursor.moveToFirst()){
                Log.d("instarkilogram", "selectId ${id} 성공")
                userTBL = userTBL(
                        cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4)
                )
            }
        }catch (e:java.lang.Exception){
            Log.d("instarkilogram", "selectId ${id} 실패")
        }
        return userTBL
    }

    fun update(userTBL: userTBL?):Boolean{
        val db:SQLiteDatabase = writableDatabase
        var flag = false
        if(userTBL != null){
            try{
                db.execSQL("update userTBL set name = '${userTBL?.name}', password = '${userTBL?.password}'," +
                        " phone = '${userTBL?.phone}', email = '${userTBL?.email}'")
                Log.d("instarkilogram", "update ${userTBL?.id} 성공")
                flag = true
            }catch(e:java.lang.Exception){
                Log.d("instarkilogram", "update ${userTBL?.id} 실패")
                flag = false
            }
        }
        return flag
    }

    fun delete(id: String): Boolean {
        val db: SQLiteDatabase = writableDatabase
        var flag = false

        try {
            db.execSQL("delete from userTBL where id = '${id}'")
            Log.d("instarkilogram", "delete ${id} 성공")
            flag = true
        } catch (e: java.lang.Exception) {
            Log.d("instarkilogram", "delete ${id} 실패")
            flag = false
        }
        return flag
    }
}