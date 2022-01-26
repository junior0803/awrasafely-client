package com.pragmanila.awrasafely.ui.preprequest.fragments

import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.pragmanila.awrasafely.R
import com.pragmanila.awrasafely.ui.preprequest.PrepRequestTabsActivity
import kotlinx.android.synthetic.main.risk_assessment_fragment.*
import kotlinx.android.synthetic.main.risk_assessment_fragment.view.*

class ExistingRiskAssessmentFragment : Fragment(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.prep_risk_assessment_fragment,container,false)

        view.tv_ts_ct_next.setOnClickListener(View.OnClickListener {
//            (activity as TestingServicesTabsActivity).nextPage()
            (activity as PrepRequestTabsActivity).nextPage()
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragment_container, ExistingModeOfRequestFragment())
            transaction?.disallowAddToBackStack()
            transaction?.commit()
        })

        view.et_dm.setOnClickListener(View.OnClickListener {
            val calendar= if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Calendar.getInstance()
            } else {
                TODO("VERSION.SDK_INT < N")
            }
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = activity?.let { it1 ->
                DatePickerDialog(it1, DatePickerDialog.OnDateSetListener
                { view, year, monthOfYear, dayOfMonth ->
                    Log.e("DATE>>>>" , "" + (monthOfYear+1) + " / " + dayOfMonth + " / " + year)
                    var bday = "" + (monthOfYear+1) + " / " + dayOfMonth + " / " + year
                    et_dm.text = Editable.Factory.getInstance().newEditable(bday)
                }, year, month, day)
            }
            datePickerDialog!!.getDatePicker().setMaxDate(System.currentTimeMillis());
            datePickerDialog!!.show()

        })

        view.et_fm.setOnClickListener(View.OnClickListener {
            val calendar= if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Calendar.getInstance()
            } else {
                TODO("VERSION.SDK_INT < N")
            }
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = activity?.let { it1 ->
                DatePickerDialog(it1, DatePickerDialog.OnDateSetListener
                { view, year, monthOfYear, dayOfMonth ->
                    Log.e("DATE>>>>" , "" + (monthOfYear+1) + " / " + dayOfMonth + " / " + year)
                    var bday = "" + (monthOfYear+1) + " / " + dayOfMonth + " / " + year
                    et_fm.text = Editable.Factory.getInstance().newEditable(bday)
                }, year, month, day)
            }
            datePickerDialog!!.getDatePicker().setMaxDate(System.currentTimeMillis());
            datePickerDialog!!.show()

        })

        view.ts_nm.setOnClickListener(View.OnClickListener {

            val builder: android.app.AlertDialog.Builder =
                android.app.AlertDialog.Builder(activity, R.style.MyAlertDialogTheme)
            builder.setTitle("Type of sex")


            val checkedItem = 0 //this will checked the item when user open the dialog

            val typeOfSex = arrayOf("Oral", "Anal Inserter", "Anal Reciever", "Vaginal Sex")
            builder.setSingleChoiceItems(typeOfSex, checkedItem,
                DialogInterface.OnClickListener { dialog, which ->
                    ts_nm.text = Editable.Factory.getInstance().newEditable(typeOfSex[which])
//                    Toast.makeText(
//                        activity,
//                        "Position: " + which + " Value: " + listItems[which],
//                        Toast.LENGTH_LONG
//                    ).show()
                })

//            val typeOfSex = arrayOf("Oral", "Anal Inserter", "Anal Reciever", "Vaginal Sex")
//            val checkedItems = booleanArrayOf(false, false, false, false, false)
//            builder.setMultiChoiceItems(typeOfSex, checkedItems,
//                OnMultiChoiceClickListener { dialog, which, isChecked ->
//                    // The user checked or unchecked a box
////                    var allCheckItems = "";
////                    for (i in typeOfSex) {
////                        if(isChecked){
////                            allCheckItems += typeOfSex[which] + ","
////                        }
////                    }
////                    ts_nm.text = Editable.Factory.getInstance().newEditable(allCheckItems)
//                })

            builder.setPositiveButton("Confirm",
                DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })

            val dialog: android.app.AlertDialog? = builder.create()
            dialog?.show()

        })

        view.cu_nm.setOnClickListener(View.OnClickListener {

            val builder: android.app.AlertDialog.Builder =
                android.app.AlertDialog.Builder(activity, R.style.MyAlertDialogTheme)
            builder.setTitle("Condom Use")


            val checkedItem = 0 //this will checked the item when user open the dialog

            val typeOfSex = arrayOf("Always", "Sometimes", "Never")
            builder.setSingleChoiceItems(typeOfSex, checkedItem,
                DialogInterface.OnClickListener { dialog, which ->
                    cu_nm.text = Editable.Factory.getInstance().newEditable(typeOfSex[which])
//                    Toast.makeText(
//                        activity,
//                        "Position: " + which + " Value: " + listItems[which],
//                        Toast.LENGTH_LONG
//                    ).show()
                })

//            val typeOfSex = arrayOf("Oral", "Anal Inserter", "Anal Reciever", "Vaginal Sex")
//            val checkedItems = booleanArrayOf(false, false, false, false, false)
//            builder.setMultiChoiceItems(typeOfSex, checkedItems,
//                OnMultiChoiceClickListener { dialog, which, isChecked ->
//                    // The user checked or unchecked a box
////                    var allCheckItems = "";
////                    for (i in typeOfSex) {
////                        if(isChecked){
////                            allCheckItems += typeOfSex[which] + ","
////                        }
////                    }
////                    ts_nm.text = Editable.Factory.getInstance().newEditable(allCheckItems)
//                })

            builder.setPositiveButton("Confirm",
                DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })

            val dialog: android.app.AlertDialog? = builder.create()
            dialog?.show()

        })

        view.ts_nm.doAfterTextChanged {
            checkEditText(view)
        }


        view.cu_nm.doAfterTextChanged {
            checkEditText(view)
        }



        val radioGroup = view.findViewById<RadioGroup>(R.id.rg)
        radioGroup?.setOnCheckedChangeListener { group, checkedId ->
            if(R.id.radio_yes == checkedId){
                radio_yes.setBackgroundResource(R.drawable.rounded_rectangle_clicked)
                radio_no.setBackgroundResource(R.drawable.rounded_rectangle_unclicked)
            } else if(R.id.radio_no == checkedId){
                radio_no.setBackgroundResource(R.drawable.rounded_rectangle_clicked)
                radio_yes.setBackgroundResource(R.drawable.rounded_rectangle_unclicked)
            }
//            var text = "You selected: "
//            text += if (R.id.radio_male == checkedId) "Male" else "Female"
//            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        }
        radioGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->

            val radio: RadioButton = group.findViewById(checkedId)
//            Log.e("selectedtext-->",radio.text.toString())
            checkEditText(view)

        })
        return view
    }

    private fun checkEditText(view: View){
        if (TextUtils.isEmpty(view.ts_nm.text.toString()) ||
            TextUtils.isEmpty(view.cu_nm.text.toString()) ||
            rg.getCheckedRadioButtonId() == -1){
            view.tv_ts_ct_next.setBackgroundResource(R.drawable.rounded_rectangle_blue_disabled)
            view.tv_ts_ct_next.isEnabled = false
            view.tv_ts_ct_next.isClickable = false
        } else {
            view.tv_ts_ct_next.setBackgroundResource(R.drawable.rounded_rectangle_blue_enabled)
            view.tv_ts_ct_next.isEnabled = true
            view.tv_ts_ct_next.isClickable = true
        }
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }
}