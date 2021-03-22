package com.example.lab3_shengeliya.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.lab3_shengeliya.R
import com.example.lab3_shengeliya.database.DatabaseHandler
import com.example.lab3_shengeliya.entities.Book

class FragmentForm : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    internal interface OnFragmentFormDataListener {
        fun onSendData(data: String?)
    }

    private var fragmentFormDataListener: OnFragmentFormDataListener? = null

    override fun onAttach(context: android.content.Context) {
        super.onAttach(context)
        fragmentFormDataListener = try {
            context as OnFragmentFormDataListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context должен реализовывать интерфейс OnFragmentInteractionListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_form, container, false)

        val spinner = view?.findViewById<Spinner>(R.id.authors_spinner)
        ArrayAdapter.createFromResource(requireContext(), R.array.authors_array, R.layout.spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner?.adapter = adapter
            }

        val okButton = view?.findViewById<Button>(R.id.ok)
        okButton?.setOnClickListener {
            val spinner = view?.findViewById<Spinner>(R.id.authors_spinner)
            val radioGroup = view?.findViewById<RadioGroup>(R.id.radio)

            var textFinal = ""

            if (spinner.selectedItem.toString() == "")
                Toast.makeText(context, "Author doesn't checked", Toast.LENGTH_SHORT).show()
            else if (radioGroup.checkedRadioButtonId == -1)
                Toast.makeText(context, "Year doesn't checked", Toast.LENGTH_SHORT).show()
            else
            {
                val radioText = radioGroup.findViewById<RadioButton>(radioGroup.checkedRadioButtonId).text.toString()
                textFinal = "Selected info:\n" + "Author - " + spinner.selectedItem.toString() + "\n" + "Published in " + radioText;

                var book = Book(spinner.selectedItem.toString(), radioText)
                var db = DatabaseHandler(requireContext())
                db.insertBook(book)

                spinner.setSelection(0)
                radioGroup.clearCheck()
            }

            fragmentFormDataListener?.onSendData(textFinal);
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = FragmentForm()
    }
}