package com.example.lab3_shengeliya.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.lab3_shengeliya.R
import com.example.lab3_shengeliya.database.DatabaseHandler

class FragmentResult : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun setSelectedItem(selectedItem: String?) {
        var db = DatabaseHandler(requireContext())
        var data = db.readBooks()
        var result = ""

        for (i in 0 until data.size){
            result += data[i].id.toString() + " " + data[i].author + " " + data[i].publishedIn + "\n"
        }

        val view = view?.findViewById<TextView>(R.id.textFinal)
        view?.text = selectedItem
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = FragmentResult()
    }
}