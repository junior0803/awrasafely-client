package com.pragmanila.awrasafely.ui.testingservices.fragments

import android.app.DatePickerDialog
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.pragmanila.awrasafely.R
import com.pragmanila.awrasafely.ui.testingservices.TestingServicesTabsActivity
//import kotlinx.android.synthetic.main.arv_risk_assessment_fragment.*
import kotlinx.android.synthetic.main.new_personal_information_fragment.*
import kotlinx.android.synthetic.main.new_personal_information_fragment.view.*
import kotlinx.android.synthetic.main.prep_risk_assessment_fragment.view.*
import kotlinx.android.synthetic.main.risk_assessment_fragment.*
import kotlinx.android.synthetic.main.risk_assessment_fragment.cu_nm
import kotlinx.android.synthetic.main.risk_assessment_fragment.et_dm
import kotlinx.android.synthetic.main.risk_assessment_fragment.et_fm
import kotlinx.android.synthetic.main.risk_assessment_fragment.rg
import kotlinx.android.synthetic.main.risk_assessment_fragment.ts_nm
import kotlinx.android.synthetic.main.risk_assessment_fragment.view.*
import kotlinx.android.synthetic.main.risk_assessment_fragment.view.cu_nm
import kotlinx.android.synthetic.main.risk_assessment_fragment.view.ef_nm
import kotlinx.android.synthetic.main.risk_assessment_fragment.view.et_dm
import kotlinx.android.synthetic.main.risk_assessment_fragment.view.et_fm
import kotlinx.android.synthetic.main.risk_assessment_fragment.view.et_nm
import kotlinx.android.synthetic.main.risk_assessment_fragment.view.radio_no
import kotlinx.android.synthetic.main.risk_assessment_fragment.view.radio_yes
import kotlinx.android.synthetic.main.risk_assessment_fragment.view.ts_nm
import kotlinx.android.synthetic.main.risk_assessment_fragment.view.tv_ts_ct_next
import java.util.*
import kotlinx.android.synthetic.main.risk_assessment_fragment.et_nm as et_nm1
import android.text.Spanned

import android.text.InputFilter
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import kotlinx.android.synthetic.main.arv_risk_assessment_fragment.*
import kotlinx.android.synthetic.main.personal_information_fragment.view.*
import kotlinx.android.synthetic.main.risk_assessment_fragment.ef_nm
import kotlinx.android.synthetic.main.risk_assessment_fragment.et_nm
import java.lang.Exception
import java.util.regex.Pattern


class RiskAssessmentFragment : Fragment() {

//    var et: String? = view?.et_nm?.text.toString()

    private fun nPattern(text: String?): Boolean {
        var p = Pattern.compile("[0-9]{4}")
        var m = p.matcher(text)
        return m.matches()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.risk_assessment_fragment, container, false)

//        view.ll_existing_client.setOnClickListener(View.OnClickListener {
//            view.iv_existing_check.visibility = View.VISIBLE
//            view.iv_new_check.visibility = View.GONE
//            ll_existing_client.setBackgroundResource(R.drawable.rounded_rectangle_clicked)
//            ll_new_client.setBackgroundResource(R.drawable.rounded_rectangle)
//            tv_ts_ct_next.visibility = View.VISIBLE
//        })
//
//        view.ll_new_client.setOnClickListener(View.OnClickListener {
//            view.iv_existing_check.visibility = View.GONE
//            view.iv_new_check.visibility = View.VISIBLE
//            ll_new_client.setBackgroundResource(R.drawable.rounded_rectangle_clicked)
//            ll_existing_client.setBackgroundResource(R.drawable.rounded_rectangle)
//            tv_ts_ct_next.visibility = View.VISIBLE
//        })
        view.tv_ts_ct_next.setOnClickListener {
            if (checkEditText(view)) {
                (activity as TestingServicesTabsActivity).nextPage()

                //passing data to summary
//                val transaction = activity?.supportFragmentManager?.beginTransaction()
//                transaction?.replace(R.id.fragment_container, ModeOfRequestFragment())
//                transaction?.addToBackStack(null)
//                transaction?.commit()
            } else {
                checkEditText(view)
            }
        }


        view.et_dm.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                et_dm.setBackgroundResource(R.drawable.textbox_unclicked)
            } else if (hasFocus) {

                et_nm1.text = Editable.Factory.getInstance()
                    .newEditable(et_nm1.text.toString().replaceFirst("^0+(?!$)".toRegex(), ""))

                view.et_dm.setBackgroundResource(R.drawable.textbox_clicked)
                val calendar = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    Calendar.getInstance()
                } else {
                    TODO("VERSION.SDK_INT < N")
                }
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)
                val datePickerDialog = activity?.let { it1 ->
                    DatePickerDialog(
                        it1, { _, year, monthOfYear, dayOfMonth ->
                            val bday = "" + (monthOfYear + 1) + " / " + dayOfMonth + " / " + year
                            et_dm.text = Editable.Factory.getInstance().newEditable(bday)


                        }, year, month, day
                    )
                }
                datePickerDialog!!.datePicker.minDate = System.currentTimeMillis() - 31557600000
                datePickerDialog!!.datePicker.maxDate = System.currentTimeMillis()
                datePickerDialog.show()
                ti_dm.error = null
            }
        }

        view.et_dm.setOnClickListener {
            view.et_dm.setBackgroundResource(R.drawable.textbox_clicked)
            val calendar = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Calendar.getInstance()
            } else {
                TODO("VERSION.SDK_INT < N")
            }
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = activity?.let { it1 ->
                DatePickerDialog(
                    it1, { _, year, monthOfYear, dayOfMonth ->
                        val bday = "" + (monthOfYear + 1) + " / " + dayOfMonth + " / " + year
                        et_dm.text = Editable.Factory.getInstance().newEditable(bday)
                    }, year, month, day
                )
            }
            datePickerDialog!!.datePicker.minDate = System.currentTimeMillis() - 31557600000
            datePickerDialog!!.datePicker.maxDate = System.currentTimeMillis()
            datePickerDialog.show()
            ti_dm.error = null
        }


        view.dt_tx.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                if (TextUtils.isEmpty(view.dt_tx.text.toString())) {
                    view.dt_in.error = "This field is required"
                    view.dt_tx.setBackgroundResource(R.drawable.textbox_error_bg)
                    view.dt_in.requestFocus()
                } else {
                    view.dt_in.error = null
                    view.dt_in.isErrorEnabled = false
                    view.dt_tx.setBackgroundResource(R.drawable.textbox_unclicked)
                }
            } else if (hasFocus) {
                if (view.dt_in.error == null) {
                    view.dt_tx.setBackgroundResource(R.drawable.textbox_clicked)
                    view.dt_in.isErrorEnabled = false
                    view.dt_in.error = null
                    val calendar = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Calendar.getInstance()
                    } else {
                        TODO("VERSION.SDK_INT < N")
                    }
                    val year = calendar.get(Calendar.YEAR)
                    val month = calendar.get(Calendar.MONTH)
                    val day = calendar.get(Calendar.DAY_OF_MONTH)
                    val datePickerDialog = activity?.let { it1 ->
                        DatePickerDialog(
                            it1, { _, year, monthOfYear, dayOfMonth ->
                                val bday =
                                    "" + (monthOfYear + 1) + " / " + dayOfMonth + " / " + year
                                dt_tx.text = Editable.Factory.getInstance().newEditable(bday)
                                dt_in.error = null
                                dt_tx.setBackgroundResource(R.drawable.textbox_unclicked)
                                dt_in.isErrorEnabled = false
                            }, year, month, day
                        )
                    }
                    datePickerDialog!!.datePicker.maxDate = System.currentTimeMillis()
                    datePickerDialog.show()
                    dt_in.error = null
                } else if (view.dt_in.error != null) {
                    view.dt_tx.setBackgroundResource(R.drawable.textbox_error_bg)
                    view.dt_in.isErrorEnabled = true
                    view.dt_in.error = "This field is required"
                } else {
                    view.dt_tx.setBackgroundResource(R.drawable.textbox_unclicked)
                    view.dt_tx.error = null
                }
            }
        }



        view.dt_tx.setOnClickListener {
            view.dt_tx.setBackgroundResource(R.drawable.textbox_clicked)
            val calendar = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Calendar.getInstance()
            } else {
                TODO("VERSION.SDK_INT < N")
            }
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = activity?.let { it1 ->
                DatePickerDialog(
                    it1, { _, year, monthOfYear, dayOfMonth ->
                        val bday = "" + (monthOfYear + 1) + " / " + dayOfMonth + " / " + year
                        dt_tx.text = Editable.Factory.getInstance().newEditable(bday)
                        dt_tx.setBackgroundResource(R.drawable.textbox_unclicked)
                        dt_in.error = null
                        dt_in.isErrorEnabled = false
                    }, year, month, day
                )
            }
            datePickerDialog!!.datePicker.maxDate = System.currentTimeMillis()
            datePickerDialog.show()
            dt_in.error = null
        }


        view.et_fm.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                if (view.ti_fm.error != null) {
                    et_fm.setBackgroundResource(R.drawable.textbox_error_bg)

                } else {
                    et_fm.setBackgroundResource(R.drawable.textbox_unclicked)
                    ti_fm.error = null
                    ti_fm.isErrorEnabled = false
                }
            } else if (hasFocus) {
                view.et_fm.setBackgroundResource(R.drawable.textbox_clicked)
                val calendar = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    Calendar.getInstance()
                } else {
                    TODO("VERSION.SDK_INT < N")
                }
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)
                val datePickerDialog = activity?.let { it1 ->
                    DatePickerDialog(
                        it1, { _, year, monthOfYear, dayOfMonth ->
                            val bday = "" + (monthOfYear + 1) + " / " + dayOfMonth + " / " + year
                            et_fm.text = Editable.Factory.getInstance().newEditable(bday)
                            ti_fm.error = null
                        }, year, month, day
                    )
                }
                datePickerDialog!!.datePicker.minDate = System.currentTimeMillis() - 31557600000
                datePickerDialog!!.datePicker.maxDate = System.currentTimeMillis()
                datePickerDialog.show()
                ti_fm.error = null
            }
        }

        view.et_fm.setOnClickListener {
            view.et_fm.setBackgroundResource(R.drawable.textbox_clicked)
            val calendar = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Calendar.getInstance()
            } else {
                TODO("VERSION.SDK_INT < N")
            }
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = activity?.let { it1 ->
                DatePickerDialog(
                    it1, { _, year, monthOfYear, dayOfMonth ->
                        val bday = "" + (monthOfYear + 1) + " / " + dayOfMonth + " / " + year
                        et_fm.text = Editable.Factory.getInstance().newEditable(bday)
                        ti_fm.error = null

                    }, year, month, day
                )
            }
            datePickerDialog!!.datePicker.minDate = System.currentTimeMillis() - 31557600000
            datePickerDialog!!.datePicker.maxDate = System.currentTimeMillis()
            datePickerDialog.show()
            view.ti_fm.error = null
        }

        view.ts_nm.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                view.ts_nm.setBackgroundResource(R.drawable.textbox_unclicked)
            } else if (hasFocus) {
                view.ts_nm.setBackgroundResource(R.drawable.textbox_clicked)
                val sexArray = arrayOf(
                    "Anal Inserter",
                    "Anal Receiver",
                    "Oral",
                    "Vaginal"
                ) // Where we track the selected items
                val builder = android.app.AlertDialog.Builder(activity, R.style.MyAlertDialogTheme)
                val selectedList = ArrayList<Int>()
                val selectedSex = booleanArrayOf(false, false, false, false)
                // Set the dialog title
                builder.setTitle("Type of sex")
                    // Specify the list array, the items to be selected by default (null for none),
                    // and the listener through which to receive callbacks when items are selected
                    .setMultiChoiceItems(
                        sexArray, selectedSex
                    ) { _, which, isChecked ->
                        selectedSex[which] = isChecked
                        if (isChecked) {
                            selectedList.add(which)
                        } else if (selectedList.contains(which)) {
                            selectedList.remove(Integer.valueOf(which))
                        }
                        // Get the current focused item
                        // Notify the current action
                    }

                    // Set the action buttons
                    .setPositiveButton(
                        "Confirm"
                    ) { _, _ ->
                        // User clicked OK, so save the selectedItems results somewhere
                        // or return them to the component that opened the dialogString selectedIndex = "";
                        val selectedStrings = ArrayList<String>()
                        ts_nm.error = null
                        for (j in selectedList.indices) {
                            selectedStrings.add(sexArray[selectedList[j]])
                        }
                        ts_nm.text = selectedStrings.toString().replace("[", "")
                            .replace("]", "");
                    }
                    .setNegativeButton(
                        R.string.cancel
                    ) { _, _ ->

                    }

                builder.show()
                ts_nm.error = null
            }
        }

        view.ts_nm.setOnClickListener {
            view.ts_nm.setBackgroundResource(R.drawable.textbox_clicked)
            val sexArray = arrayOf(
                "Anal Inserter",
                "Anal Receiver",
                "Oral",
                "Vaginal"
            ) // Where we track the selected items
            val builder = android.app.AlertDialog.Builder(activity, R.style.MyAlertDialogTheme)
            val selectedList = ArrayList<Int>()
            val selectedSex = booleanArrayOf(false, false, false, false)
            // Set the dialog title
            builder.setTitle("Type of sex")
                // Specify the list array, the items to be selected by default (null for none),
                // and the listener through which to receive callbacks when items are selected
                .setMultiChoiceItems(
                    sexArray, selectedSex
                ) { _, which, isChecked ->
                    selectedSex[which] = isChecked
                    if (isChecked) {
                        selectedList.add(which)
                    } else if (selectedList.contains(which)) {
                        selectedList.remove(Integer.valueOf(which))
                    }
                    // Get the current focused item
                    // Notify the current action
                }

                // Set the action buttons
                .setPositiveButton(
                    "Confirm"
                ) { _, _ ->
                    // User clicked OK, so save the selectedItems results somewhere
                    // or return them to the component that opened the dialogString selectedIndex = "";
                    val selectedStrings = ArrayList<String>()
                    ts_nm.error = null
                    for (j in selectedList.indices) {
                        selectedStrings.add(sexArray[selectedList[j]])
                    }
                    ts_nm.text = selectedStrings.toString().replace("[", "")
                        .replace("]", "");
                }
                .setNegativeButton(
                    R.string.cancel
                ) { _, _ ->

                }

            builder.show()
            ts_nm.error = null
        }


        view.cu_nm.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                if (view.cu_nm.text.toString().trim().isEmpty()) {
                    view.cu_nm.error = "This field is required"
                    view.cu_nm.setBackgroundResource(R.drawable.textbox_error_bg)
                } else {
                    // your code here
                    view.cu_nm.error = null
                    view.cu_nm.setBackgroundResource(R.drawable.textbox_unclicked)
                }
            } else if (hasFocus) {
                if (view.cu_nm.error != null) {
                    view.cu_nm.setBackgroundResource(R.drawable.textbox_error_bg)
                    view.cu_nm.error = "This field is required"
                } else {
                    view.cu_nm.setBackgroundResource(R.drawable.textbox_clicked)
                    val builder: android.app.AlertDialog.Builder =
                        android.app.AlertDialog.Builder(activity, R.style.MyAlertDialogTheme)
                    builder.setTitle("Condom Use")

                    val checkedItem: Int = when {
                        cu_nm.text.toString() == "Always" -> {
                            0
                        }
                        cu_nm.text.toString() == "Sometimes" -> {
                            1
                        }
                        cu_nm.text.toString() == "Never" -> {
                            2
                        }
                        else -> {
                            -1
                        }
                    }

                    val typeOfSex = arrayOf("Always", "Sometimes", "Never")
                    builder.setSingleChoiceItems(
                        typeOfSex, checkedItem
                    ) { _, which ->
                        cu_nm.text = Editable.Factory.getInstance().newEditable(typeOfSex[which])
                    }

                    builder.setPositiveButton(
                        "Confirm"
                    ) { dialog, _ ->
                        dialog.dismiss()
                    }

                    val dialog: android.app.AlertDialog? = builder.create()
                    dialog?.show()
                }
            }
        }

        view.cu_nm.setOnClickListener {
            view.cu_nm.setBackgroundResource(R.drawable.textbox_clicked)
            val builder: android.app.AlertDialog.Builder =
                android.app.AlertDialog.Builder(activity, R.style.MyAlertDialogTheme)
            builder.setTitle("Condom Use")

            val checkedItem: Int = when {
                cu_nm.text.toString() == "Always" -> {
                    0
                }
                cu_nm.text.toString() == "Sometimes" -> {
                    1
                }
                cu_nm.text.toString() == "Never" -> {
                    2
                }
                else -> {
                    -1
                }
            }

            val typeOfSex = arrayOf("Always", "Sometimes", "Never")
            builder.setSingleChoiceItems(
                typeOfSex, checkedItem
            ) { _, which ->
                cu_nm.text = Editable.Factory.getInstance().newEditable(typeOfSex[which])
            }

            builder.setPositiveButton(
                "Confirm"
            ) { dialog, _ ->
                dialog.dismiss()
            }

            val dialog: android.app.AlertDialog? = builder.create()
            dialog?.show()
        }


        view.et_nm.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {

                et_nm.text = Editable.Factory.getInstance()
                    .newEditable(et_nm.text.toString().replaceFirst("^0+(?!$)".toRegex(), ""))

                when {
                    TextUtils.isEmpty(view.et_nm.text.toString()) -> {
                        view.et_nm.setBackgroundResource(R.drawable.textbox_error_bg)
                        view.ti_mn.error = "This field is required"
                        view.ti_dm.isEnabled = false
                        view.ti_mn.isErrorEnabled = true
                        view.et_dm.setBackgroundResource(R.color.gray_100)
                    }
                    view.et_nm.text.toString() != "0" -> {
                        view.ti_dm.isEnabled = true
                        view.et_nm.setBackgroundResource(R.drawable.textbox_unclicked)
                        view.et_dm.setBackgroundResource(R.drawable.background_white)
                        view.ti_mn.error = null
                    }
                    view.et_nm.text.toString() == "" -> {
                        view.et_nm.setBackgroundResource(R.drawable.textbox_error_bg)
                        view.ti_mn.error = "This field is required"
                        view.ti_dm.isEnabled = false
                        view.ti_mn.isErrorEnabled = true
                        view.et_dm.setBackgroundResource(R.color.gray_100)
                    }

                    view.et_nm.text.toString().toInt() < 1 -> {
                        view.ti_mn.error = null
                        view.ti_dm.isEnabled = false
                        view.ti_mn.isErrorEnabled = false
                        view.et_nm.setBackgroundResource(R.drawable.textbox_unclicked)
                        view.et_dm.setBackgroundResource(R.color.gray_100)
                    }
                    else -> {
                        // your code here
                        view.ti_mn.error = null
                        view.ti_dm.isEnabled = false
                        view.ti_mn.isErrorEnabled = false
                        view.et_nm.setBackgroundResource(R.drawable.textbox_unclicked)
                        view.et_dm.setBackgroundResource(R.color.gray_100)

                    }
                }
            } else if (hasFocus) {
                Log.d("debug1", view.et_nm.text.toString())


                view.et_nm.setBackgroundResource(R.drawable.textbox_clicked)
            }
        }





        view.et_nm.doOnTextChanged { _, _, _, _ ->
            Log.d("debug", et_nm1.text.toString())
            // view.et_nm.inputFilterNumberRange(0..1000)

            when {

                view.et_nm.text.toString() != "0" && view.et_nm.text.toString() != "" && view.et_nm.text.toString()
                    .toInt() >= 1 -> {
                    view.ti_dm.isEnabled = true
                    view.ti_dm.isFocusableInTouchMode = true
                    view.ti_dm.isClickable = true
                    view.ti_mn.error = null
                    view.ti_mn.isErrorEnabled = false
                    view.ti_dm.isFocusable = true
                    view.et_dm.setBackgroundResource(R.drawable.background_white)
                }
                view.et_nm.text.toString().isEmpty() || view.et_nm.text.toString() == "" -> {
                    view.ti_dm.isEnabled = false
                    view.ti_dm.isClickable = false
                    view.ti_mn.isErrorEnabled = false
                    view.et_dm.setBackgroundResource(R.color.gray_100)
                }
//                et_nm.text!=null->{
//                    et_nm.text= Editable.Factory.getInstance().newEditable(et_nm.text.toString())
//                }
                else -> {
                    // your code here
                    view.ti_dm.isErrorEnabled = false
                    view.ti_dm.isEnabled = false
                    view.ti_mn.isErrorEnabled = false
                    view.et_dm.setText(null)
                    view.et_dm.setBackgroundResource(R.color.gray_100)
                }
            }
        }


        view.ef_nm.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {

                ef_nm.text = Editable.Factory.getInstance()
                    .newEditable(ef_nm.text.toString().replaceFirst("^0+(?!$)".toRegex(), ""))

                when {
                    TextUtils.isEmpty(view.ef_nm.text.toString()) -> {
                        view.ef_nm.setBackgroundResource(R.drawable.textbox_error_bg)
                        view.ti_ef.error = "This field is required"
                        view.ti_fm.isEnabled = false
                        view.ti_ef.isErrorEnabled = true
                        view.ti_fm.setBackgroundResource(R.color.gray_100)
                    }
                    view.ef_nm.text.toString() != "0" -> {
                        view.ti_fm.isEnabled = true
                        view.ef_nm.setBackgroundResource(R.drawable.textbox_unclicked)
                        view.et_fm.setBackgroundResource(R.drawable.background_white)
                        view.ti_ef.error = null
                    }
                    view.ef_nm.text.toString() == "" -> {
                        view.ef_nm.setBackgroundResource(R.drawable.textbox_error_bg)
                        view.ti_ef.error = "This field is required"
                        view.ti_fm.isEnabled = false
                        view.ti_ef.isErrorEnabled = true
                        view.et_fm.setBackgroundResource(R.color.gray_100)
                    }
                    else -> {
                        // your code here
                        view.ti_ef.error = null
                        view.ti_fm.isEnabled = false
                        view.ti_fm.isErrorEnabled = false
                        view.ef_nm.setBackgroundResource(R.drawable.textbox_unclicked)
                        view.et_fm.setBackgroundResource(R.color.gray_100)

                    }
                }
            } else if (hasFocus) {
                Log.d("debug1", view.et_nm.text.toString())
                //ef_nm.text = Editable.Factory.getInstance().newEditable(ef_nm.text.toString().replaceFirst("^0+(?!$)".toRegex(), ""))

                view.ef_nm.setBackgroundResource(R.drawable.textbox_clicked)
            }
        }


        view.ef_nm.doOnTextChanged { _, _, _, _ ->
            Log.d("debug", et_nm1.text.toString())
            // view.et_nm.inputFilterNumberRange(0..1000)

            when {

                view.ef_nm.text.toString() != "0" && view.ef_nm.text.toString() != "" && view.ef_nm.text.toString()
                    .toInt() >= 1 -> {
                    view.ti_fm.isEnabled = true
                    view.ti_fm.isFocusableInTouchMode = true
                    view.ti_fm.isClickable = true
                    view.ti_ef.error = null
                    view.ti_ef.isErrorEnabled = false
                    view.ti_fm.isFocusable = true
                    view.et_fm.setBackgroundResource(R.drawable.background_white)
                }
                view.ef_nm.text.toString()
                    .isEmpty() || view.ef_nm.text.toString() == "" || view.ef_nm.text.toString() == "0" -> {
                    view.ti_fm.isEnabled = false
                    view.ti_fm.isClickable = false
                    view.ti_ef.isErrorEnabled = false
                    view.et_fm.setBackgroundResource(R.color.gray_100)
                }
//                et_nm.text!=null->{
//                    et_nm.text= Editable.Factory.getInstance().newEditable(et_nm.text.toString())
//                }
                else -> {
                    // your code here
                    view.ti_fm.isErrorEnabled = false
                    view.ti_fm.isEnabled = false
                    view.ti_fm.isErrorEnabled = false
//                    view.ef_nm.setText(null)
                    view.et_fm.setBackgroundResource(R.color.gray_100)
                }
            }
        }

        view.et_dm.doOnTextChanged { _, _, _, _ ->
            if (TextUtils.isEmpty(
                    view.et_dm.text.toString()
                ) && view.ti_dm.isEnabled
            ) {
                view.ti_dm.error = "This field is required"
                view.ti_dm.isErrorEnabled = true
                view.ti_dm.requestFocus()
            } else {
                view.ti_dm.isErrorEnabled = false

            }
        }


        if (view.radio_yes.isChecked()) {
            // is checked
            view.ln_id.visibility = View.VISIBLE
        } else {
            // not checked
            view.ln_id.visibility = View.GONE

        }
        view.radio_yes.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                if (view.radio_yes.isChecked == true) {
                    view.ln_id.visibility = View.VISIBLE
                }
                view.radio_yes.setBackgroundResource(R.drawable.textbox_unclicked)

            } else if (hasFocus) {
                view.radio_yes.isChecked = true
                view.radio_yes.setBackgroundResource(R.drawable.textbox_clicked)
                view.radio_no.isChecked = false
                view.radio_no.setBackgroundResource(R.drawable.textbox_unclicked)
                view.ln_id.visibility = View.VISIBLE
            }
        }

        view.radio_no.setOnClickListener() {
            view.radio_yes.isChecked = false
            view.radio_yes.setBackgroundResource(R.drawable.textbox_unclicked)
            view.radio_no.setBackgroundResource(R.drawable.textbox_clicked)
            view.radio_no.isChecked = true
            view.radio_no.setError(null)
        }
        view.radio_no.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                view.radio_no.setBackgroundResource(R.drawable.textbox_unclicked)
                view.radio_no.error = null
            } else if (hasFocus) {
                if (view.radio_no.error != null) {
                    view.radio_no.isChecked = false
                    view.radio_no.setBackgroundResource(R.drawable.textbox_error_bg)
                    view.radio_yes.setBackgroundResource(R.drawable.textbox_error_bg)
                    view.radio_yes.isChecked = false
                } else if (view.radio_no.error == null) {
                    view.radio_yes.isChecked = false
                    view.radio_no.setBackgroundResource(R.drawable.textbox_clicked)
                    view.radio_no.error == null
                    view.radio_yes.setBackgroundResource(R.drawable.textbox_unclicked)
                    view.radio_no.isChecked = true
                    view.ln_id.visibility = View.GONE
                }

            }
        }

//        radioGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
//
//            val radio: RadioButton = group.findViewById(checkedId)
////            Log.e("selectedtext-->",radio.text.toString())
//            checkEditText(view)
//
//        })

        view.so_tx.setOnFocusChangeListener() { _, hasFocus ->
            if (!hasFocus) {
                if (TextUtils.isEmpty(view.so_tx.text.toString())) {
                    view.so_ne.error = "This field is required"
                    view.so_tx.setBackgroundResource(R.drawable.textbox_error_bg)
                    view.so_ne.requestFocus()
                } else {
                    view.so_ne.error = null
                    view.so_ne.isErrorEnabled = false
                    view.so_tx.setBackgroundResource(R.drawable.textbox_unclicked)
                }
            } else if (hasFocus) {
                if (view.so_ne.error == null) {
                    view.so_tx.setBackgroundResource(R.drawable.textbox_clicked)
                    val builder: android.app.AlertDialog.Builder =
                        android.app.AlertDialog.Builder(activity, R.style.MyAlertDialogTheme)
                    builder.setTitle("Source of Needles")

                    //this will checked the item when user open the dialog

                    val sourceOfNeedles = arrayOf(
                        "Shooting Gallery",
                        "Pharmacy",
                        "Trash",
                        "Social Hygiene Clinic",
                        "Non Government Organization",
                        "Other IDUs"
                    )

                    val checkedItem: Int = when {
                        so_tx.text.toString() == "Shooting Gallery" -> {
                            0
                        }
                        so_tx.text.toString() == "Pharmacy" -> {
                            1
                        }
                        so_tx.text.toString() == "Trash" -> {
                            2
                        }
                        so_tx.text.toString() == "Social Hygiene Clinic" -> {
                            3
                        }
                        so_tx.text.toString() == "Non Government Organization" -> {
                            4
                        }
                        so_tx.text.toString() == "Other IDUs" -> {
                            5
                        }
                        else -> {
                            -1
                        }
                    }

                    builder.setSingleChoiceItems(
                        sourceOfNeedles, checkedItem
                    ) { _, which ->
                        so_tx.text =
                            Editable.Factory.getInstance().newEditable(sourceOfNeedles[which])
                    }

                    builder.setPositiveButton(
                        "Confirm"
                    ) { dialog, _ ->
                        dialog.dismiss()
                        so_ne.error = null
                        so_ne.isErrorEnabled = false
                    }

                    val dialog: android.app.AlertDialog? = builder.create()
                    dialog?.show()

                } else {
                    view.so_tx.setBackgroundResource(R.drawable.textbox_error_bg)
                    view.so_ne.error = "This field is required"
                }
            }
        }
        view.so_tx.setOnClickListener {
            view.so_tx.setBackgroundResource(R.drawable.textbox_clicked)
            val builder: android.app.AlertDialog.Builder =
                android.app.AlertDialog.Builder(activity, R.style.MyAlertDialogTheme)
            builder.setTitle("Source of Needles")

            //this will checked the item when user open the dialog

            val sourceOfNeedles = arrayOf(
                "Shooting Gallery",
                "Pharmacy",
                "Trash",
                "Social Hygiene Clinic",
                "Non Government Organization",
                "Other IDUs"
            )

            val checkedItem: Int = when {
                so_tx.text.toString() == "Shooting Gallery" -> {
                    0
                }
                so_tx.text.toString() == "Pharmacy" -> {
                    1
                }
                so_tx.text.toString() == "Trash" -> {
                    2
                }
                so_tx.text.toString() == "Social Hygiene Clinic" -> {
                    3
                }
                so_tx.text.toString() == "Non Government Organization" -> {
                    4
                }
                so_tx.text.toString() == "Other IDUs" -> {
                    5
                }
                else -> {
                    -1
                }
            }

            builder.setSingleChoiceItems(
                sourceOfNeedles, checkedItem
            ) { _, which ->
                so_tx.text = Editable.Factory.getInstance().newEditable(sourceOfNeedles[which])
            }

            builder.setPositiveButton(
                "Confirm"
            ) { dialog, _ ->
                dialog.dismiss()
                so_ne.error = null
                so_ne.isErrorEnabled = false
                so_tx.setBackgroundResource(R.drawable.textbox_unclicked)
            }

            val dialog: android.app.AlertDialog? = builder.create()
            dialog?.show()

        }

        view.ve_tx.setOnFocusChangeListener() { _, hasFocus ->
            if (!hasFocus) {
                if (TextUtils.isEmpty(view.ve_tx.text.toString())) {
                    view.ve_ti.error = "This field is required"
                    view.ve_tx.setBackgroundResource(R.drawable.textbox_error_bg)
                    view.ve_ti.requestFocus()
                } else {
                    view.ve_ti.error = null
                    view.ve_ti.isErrorEnabled = false
                    view.ve_tx.setBackgroundResource(R.drawable.textbox_unclicked)
                }
            } else if (hasFocus) {
                if (view.ve_ti.error == null) {
                    view.ve_tx.setBackgroundResource(R.drawable.textbox_clicked)
                    val builder: android.app.AlertDialog.Builder =
                        android.app.AlertDialog.Builder(activity, R.style.MyAlertDialogTheme)
                    builder.setTitle("Venue of Last Injection")

                    //this will checked the item when user open the dialog
                    val public = "Public Places(Street, Park, Etc.)"

                    val venueList =
                        arrayOf("House", "Shooting Gallery", public)

                    val checkedItem: Int = when {
                        ve_tx.text.toString() == "House" -> {
                            0
                        }
                        ve_tx.text.toString() == "Shooting Gallery" -> {
                            1
                        }
                        ve_tx.text.toString() == public -> {
                            2
                        }
                        else -> {
                            -1
                        }
                    }

                    builder.setSingleChoiceItems(
                        venueList, checkedItem
                    ) { _, which ->
                        ve_tx.text = Editable.Factory.getInstance().newEditable(venueList[which])
                    }

                    builder.setPositiveButton(
                        "Confirm"
                    ) { dialog, _ ->
                        dialog.dismiss()
                        ve_ti.error = null
                        ve_ti.isErrorEnabled = false
                    }

                    val dialog: android.app.AlertDialog? = builder.create()
                    dialog?.show()

                } else {
                    view.ve_tx.setBackgroundResource(R.drawable.textbox_error_bg)
                    view.ve_ti.error = "This field is required"
                }
            }
        }

        view.ve_tx.setOnClickListener {
            view.ve_tx.setBackgroundResource(R.drawable.textbox_clicked)
            val builder: android.app.AlertDialog.Builder =
                android.app.AlertDialog.Builder(activity, R.style.MyAlertDialogTheme)
            builder.setTitle("Venue of Last Injection")


            //this will checked the item when user open the dialog
            val public = "Public Places(Street, Park, Etc.)"
            val venueList =
                arrayOf("House", "Shooting Gallery", public)

            val checkedItem: Int = when {
                ve_tx.text.toString() == "House" -> {
                    0
                }
                ve_tx.text.toString() == "Shooting Gallery" -> {
                    1
                }
                ve_tx.text.toString() == public -> {
                    2
                }
                else -> {
                    -1
                }
            }

            builder.setSingleChoiceItems(
                venueList, checkedItem
            ) { _, which ->
                ve_tx.text = Editable.Factory.getInstance().newEditable(venueList[which])


            }


            builder.setPositiveButton(
                "Confirm"
            ) { dialog, _ ->
                dialog.dismiss()
                ve_ti.error = null
                ve_ti.isErrorEnabled = false
            }

            val dialog: android.app.AlertDialog? = builder.create()
            dialog?.show()

        }


        view.sh_radio_yes.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                view.sh_radio_yes.setBackgroundResource(R.drawable.textbox_unclicked)
            } else if (hasFocus) {
                view.sh_radio_yes.isChecked = true
                view.sh_radio_yes.setBackgroundResource(R.drawable.textbox_clicked)
                view.sh_radio_no.isChecked = false
                view.sh_radio_no.error = null
                view.sh_radio_no.setBackgroundResource(R.drawable.textbox_unclicked)
            }
        }
        view.sh_radio_no.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                view.sh_radio_no.setBackgroundResource(R.drawable.textbox_unclicked)
            } else if (hasFocus) {
                if (view.sh_radio_no.error == null) {
                    view.sh_radio_yes.isChecked = false
                    view.sh_radio_yes.setBackgroundResource(R.drawable.textbox_unclicked)
                    view.sh_radio_no.setBackgroundResource(R.drawable.textbox_clicked)
                    view.sh_radio_no.isChecked = true
                } else if (view.sh_radio_no.error != null) {
                    view.sh_radio_no.isChecked = false
                    view.sh_radio_no.setBackgroundResource(R.drawable.textbox_error_bg)
                    view.sh_radio_yes.setBackgroundResource(R.drawable.textbox_error_bg)
                    view.sh_radio_yes.isChecked = false
                } else {
                    view.sh_radio_no.isChecked = true
                    view.sh_radio_no.setBackgroundResource(R.drawable.textbox_clicked)
                    view.sh_radio_yes.setBackgroundResource(R.drawable.textbox_unclicked)
                    view.sh_radio_yes.isChecked = false
                }

            }
        }

        view.sh_radio_no.setOnClickListener {
            view.sh_radio_no.setBackgroundResource(R.drawable.textbox_clicked)
            view.sh_radio_no.isChecked = true
            view.sh_radio_no.error = null
            view.sh_radio_yes.setBackgroundResource(R.drawable.textbox_unclicked)
        }
        view.sh_radio_yes.setOnClickListener {
            view.sh_radio_yes.isChecked = true
            view.sh_radio_no.setBackgroundResource(R.drawable.textbox_unclicked)
            view.sh_radio_yes.setBackgroundResource(R.drawable.textbox_clicked)
        }

        // apply edit text input filter number range
        view.et_nm.inputFilterNumberRange(0..1000)
        view.ef_nm.inputFilterNumberRange(0..1000)



        return view
    }

    fun EditText.inputFilterNumberRange(range: IntRange) {
        filterMin(range.first)
        filters = arrayOf<InputFilter>(InputFilterMax(range.last))
    }


    // class to input filter maximum number
    class InputFilterMax(private var max: Int) : InputFilter {
        override fun filter(
            p0: CharSequence, p1: Int, p2: Int, p3: Spanned?, p4: Int, p5: Int
        ): CharSequence? {
            try {
                val replacement = p0.subSequence(p1, p2).toString()
                val newVal = p3.toString().substring(0, p4) + replacement + p3.toString()
                    .substring(p5, p3.toString().length)
                val input = newVal.toInt()
                if (input <= max) return null
            } catch (e: NumberFormatException) {
            }
            return ""
        }
    }


    // extension function to filter edit text minimum number
    fun EditText.filterMin(min: Int) {
//        onFocusChangeListener = View.OnFocusChangeListener { view, b ->
//            if (!b) {
//                // set minimum number if inputted number less than minimum
//                setTextMin(min)
//                // hide soft keyboard on edit text lost focus
//                context.hideSoftKeyboard(this)
//            }
//        }
//
//        setOnEditorActionListener { v, actionId, event ->
//            if (actionId == EditorInfo.IME_ACTION_DONE) {
//                // set minimum number if inputted number less than minimum
//                setTextMin(min)
//
//                // hide soft keyboard on press action done
//                context.hideSoftKeyboard(this)
//            }
//            false
        //  }
    }


    // extension function to set edit text minimum number
    fun EditText.setTextMin(min: Int) {
        try {
            val value = text.toString().toInt()
            if (value < min) {
                setText("$min")
            }
        } catch (e: Exception) {
            setText("$min")
        }
    }


    // extension function to hide soft keyboard programmatically
    fun Context.hideSoftKeyboard(editText: EditText) {
        (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager).apply {
            hideSoftInputFromWindow(editText.windowToken, 0)
        }
    }

    private fun checkEditText(view: View): Boolean {
        if (TextUtils.isEmpty(view.et_nm.text.toString())) {
            view.ti_mn.error = "This field is required"
            view.ti_mn.requestFocus()
        } else if (view.ti_dm.isEnabled == true && TextUtils.isEmpty(view.et_dm.text.toString())) {
            view.ti_dm.error = "This field is required"
            view.ti_dm.isErrorEnabled = true
            view.ti_dm.requestFocus()
        } else if (TextUtils.isEmpty(view.ef_nm.text.toString())) {
            view.ti_ef.error = "This field is required"
            view.ti_ef.requestFocus()
        } else if (TextUtils.isEmpty(view.et_fm.text.toString()) && view.ti_fm.isEnabled == true) {
            view.ti_fm.error = "This field is required"
            view.ti_fm.requestFocus()
            view.ti_fm.isErrorEnabled = true
        } else if (view.ts_nm.text.toString().isEmpty()) {
            view.ts_nm.error = "This field is required"
            view.ts_nm.requestFocus()
        } else if (TextUtils.isEmpty(view.cu_nm.text.toString())) {
            view.cu_nm.error = "This field is required"
            view.cu_nm.requestFocus()
        } else if (rg.checkedRadioButtonId == -1) {
            view.radio_no.error = "This field is required"
            view.radio_no.requestFocus()
        } else if (ln_id.visibility == View.VISIBLE) {
            when {
                TextUtils.isEmpty(view.dt_tx.text.toString()) -> {
                    view.dt_in.error = "This field is required"
                    view.dt_in.requestFocus()
                }
                TextUtils.isEmpty(view.so_tx.text.toString()) -> {
                    view.so_ne.error = "This field is required"
                    view.so_ne.requestFocus()
                }
                TextUtils.isEmpty(view.ve_tx.text.toString()) -> {
                    view.ve_ti.error = "This field is required"
                    view.ve_ti.requestFocus()
                }
                sh_rg.checkedRadioButtonId == -1 -> {
                    view.sh_radio_no.error = "This field is required"
                    view.sh_radio_no.requestFocus()
                }
                else -> {
                    view.sh_radio_no.error = null
                    view.dt_in.error = null
                    view.dt_in.isErrorEnabled = false
                    view.so_ne.error = null
                    view.so_ne.isErrorEnabled = false
                    view.ve_ti.error = null
                    view.ve_ti.isErrorEnabled = false
                    view.radio_no.error = null

                    return true
                }
            }
        } else {
            view.ti_mn.error = null
            view.ti_mn.isErrorEnabled = false
            view.ti_dm.error = null
            view.ti_dm.isErrorEnabled = false
            view.ti_ef.error = null
            view.ti_ef.isErrorEnabled = false
            view.ti_fm.error = null
            view.ti_fm.isErrorEnabled = false
            view.ts_nm.error = null
            view.radio_no.error = null
            view.cu_nm.error = null
            //Injected drugs
            view.sh_radio_no.error = null
            view.dt_in.error = null
            view.dt_in.isErrorEnabled = false
            view.so_ne.error = null
            view.so_ne.isErrorEnabled = false
            view.ve_ti.error = null
            view.ve_ti.isErrorEnabled = false
            view.radio_no.error = null

            return true
        }
        return false

    }

    fun trimLeadingZeros(source: String): String? {
        for (i in 0 until source.length) {
            val c = source[i]
            if (c != '0') {
                return source.substring(i)
            }
        }
        return "" // or return "0";
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val et_nm = et_nm1.text?.toString()
        outState.putString("sv_et_nm", et_nm)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            val sv_et_nm = savedInstanceState!!.getString("sv_et_nm", "")
            et_nm1.text = Editable.Factory.getInstance().newEditable(sv_et_nm)
        }


    }
}