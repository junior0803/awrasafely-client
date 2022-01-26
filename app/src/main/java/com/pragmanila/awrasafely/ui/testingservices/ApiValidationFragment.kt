package com.pragmanila.awrasafely.ui.testingservices

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.pragmanila.awrasafely.R
import android.view.WindowManager

import android.widget.Button

import android.widget.EditText
import androidx.annotation.Nullable


class ApiValidationFragment : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.error_dialog, container)

        val myListView = rootView.findViewById(R.id.button) as Button
        //with arrayadapter you have to pass a textview as a resource, and that is simple_list_item_1
        myListView!!.setOnClickListener{
            dismiss()
        }


        return rootView
    }
}