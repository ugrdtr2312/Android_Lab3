package com.example.lab3_shengeliya

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.lab3_shengeliya.fragments.FragmentForm
import com.example.lab3_shengeliya.fragments.FragmentResult

class MainActivity : AppCompatActivity(), FragmentForm.OnFragmentFormDataListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun booksButton(view : View?){
        val intent = Intent(this, AllBooksActivity::class.java)
        startActivity(intent)
    }

    override fun onSendData(data: String?) {
        if (data != "") (supportFragmentManager.findFragmentById(R.id.FragmentResult) as FragmentResult?)?.setSelectedItem(data)
    }
}