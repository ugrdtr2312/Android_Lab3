package com.example.lab3_shengeliya.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.lab3_shengeliya.entities.Book

val DATABASE_NAME = "DbBooks"
val TABLE_NAME = "Books"
val COL_AUTHOR = "author"
val COL_PUBLISHEDIN = "publishedIn"
val COL_ID = "id"

class DatabaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_AUTHOR + " VARCHAR(256)," +
                COL_PUBLISHEDIN + " VARCHAR(256))";

        db?.execSQL(createTable);
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertBook(book: Book) {
        val db = this.writableDatabase

        var contentValues = ContentValues()
        contentValues.put(COL_AUTHOR, book.author)
        contentValues.put(COL_PUBLISHEDIN, book.publishedIn)

        var result = db.insert(TABLE_NAME, null, contentValues)
        if (result == (-1).toLong())
            Toast.makeText(context, "Failed to write in DB", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Successfully written", Toast.LENGTH_SHORT).show()

        db.close()
    }

    fun clearBooks(){
        val db = this.writableDatabase
        db.execSQL("delete from $TABLE_NAME")
        db.close()
    }

    fun readBooks() : MutableList<Book>{
        var list : MutableList<Book> = ArrayList()

        val db = this.readableDatabase
        val query = "select * from $TABLE_NAME order by $COL_ID desc"  //limit 5

        val result = db.rawQuery(query, null)

        if (result.moveToFirst()){
            do {
                var book = Book ()
                book.id = result.getString(0).toInt()
                book.author = result.getString(1).toString()
                book.publishedIn = result.getString(2).toString()

                list.add(book)
            } while (result.moveToNext())
        }

        result.close()
        db.close()
        return list
    }
}