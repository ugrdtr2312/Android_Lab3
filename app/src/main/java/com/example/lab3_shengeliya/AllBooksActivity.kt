package com.example.lab3_shengeliya

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.lab3_shengeliya.database.DatabaseHandler

class AllBooksActivity : AppCompatActivity() {
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_books)

        var actionBar = supportActionBar
        actionBar!!.title = "Ordered books"
        actionBar.setDefaultDisplayHomeAsUpEnabled(true)

        //show data from db
        var db = DatabaseHandler(this)
        var data = db.readBooks()
        var result = ""

        for (i in 0 until data.size){
            result += /*data[i].id.toString() + " " +*/ data[i].author + " " + data[i].publishedIn + "\n"
        }

        if (result != "") findViewById<TextView>(R.id.allBooks)?.text = result
        else Toast.makeText(this, "DB is empty", Toast.LENGTH_SHORT).show()
    }

    fun booksClear(view: View) {
        var db = DatabaseHandler(this)
        var data = db.readBooks()

        if (data.count() == 0)
            Toast.makeText(this, "DB is empty already", Toast.LENGTH_SHORT).show()
        else {
            db.clearBooks()
            findViewById<TextView>(R.id.allBooks)?.text = ""
            Toast.makeText(this, "DB was cleared", Toast.LENGTH_SHORT).show()
        }
    }
}