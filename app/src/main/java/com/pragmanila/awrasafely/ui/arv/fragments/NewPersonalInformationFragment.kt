package com.pragmanila.awrasafely.ui.arv.fragments

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
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.pragmanila.awrasafely.R
import com.pragmanila.awrasafely.ui.arv.ARVRequestTabsActivity
import kotlinx.android.synthetic.main.client_type_fragment.view.tv_ts_ct_next
import kotlinx.android.synthetic.main.new_personal_information_fragment.*
import kotlinx.android.synthetic.main.new_personal_information_fragment.view.*

class NewPersonalInformationFragment : Fragment(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.arv_new_personal_information_fragment,container,false)
        view.tv_ts_ct_next.setOnClickListener(View.OnClickListener {
            (activity as ARVRequestTabsActivity).nextPage()
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragment_container, RiskAssessmentFragment())
            transaction?.disallowAddToBackStack()
            transaction?.commit()
        })

        val calendar= if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Calendar.getInstance()
        } else {
            TODO("VERSION.SDK_INT < N")
        }
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        view.et_bd.setOnClickListener(View.OnClickListener {
            val datePickerDialog = activity?.let { it1 ->
                DatePickerDialog(it1, DatePickerDialog.OnDateSetListener
                { _, year, monthOfYear, dayOfMonth ->
                    Log.e("DATE>>>>" , "" + (monthOfYear+1) + " / " + dayOfMonth + " / " + year)
                    var bday = "" + (monthOfYear+1) + " / " + dayOfMonth + " / " + year
                    et_bd.text = Editable.Factory.getInstance().newEditable(bday)
                }, year, month, day)
            }
            datePickerDialog!!.getDatePicker().setMaxDate(System.currentTimeMillis());
            datePickerDialog.show()
        })

        view.et_mn.doAfterTextChanged {
            checkEditText(view)
        }
        view.et_fn.doAfterTextChanged {
            checkEditText(view)
        }
        view.et_bo.doAfterTextChanged {
            checkEditText(view)
        }
        view.ef_si.doAfterTextChanged {
            checkEditText(view)
        }
        view.ts_em.doAfterTextChanged {
            checkEditText(view)
        }
        view.et_mobile.doAfterTextChanged {
            checkEditText(view)
        }


        val radioGroup = view.findViewById<RadioGroup>(R.id.rg)
        radioGroup?.setOnCheckedChangeListener { _, checkedId ->
            if(R.id.radio_male == checkedId){
                radio_male.setBackgroundResource(R.drawable.rounded_rectangle_clicked)
                radio_female.setBackgroundResource(R.drawable.rounded_rectangle_unclicked)
            } else if(R.id.radio_female == checkedId){
                radio_female.setBackgroundResource(R.drawable.rounded_rectangle_clicked)
                radio_male.setBackgroundResource(R.drawable.rounded_rectangle_unclicked)
            }
//            var text = "You selected: "
//            text += if (R.id.radio_male == checkedId) "Male" else "Female"
//            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        }


        view.ef_si.setOnClickListener(View.OnClickListener {
            val listItems = arrayOf("Male", "Female", "Others")

            val builder: android.app.AlertDialog.Builder =
                android.app.AlertDialog.Builder(activity, R.style.MyAlertDialogTheme)
            builder.setTitle("Self Identity")


            val checkedItem = 0 //this will checked the item when user open the dialog

            builder.setSingleChoiceItems(listItems, checkedItem,
                DialogInterface.OnClickListener { _, which ->
                    ef_si.text = Editable.Factory.getInstance().newEditable(listItems[which])
//                    Toast.makeText(
//                        activity,
//                        "Position: " + which + " Value: " + listItems[which],
//                        Toast.LENGTH_LONG
//                    ).show()
                })

            builder.setPositiveButton("Confirm",
                DialogInterface.OnClickListener { dialog, _ -> dialog.dismiss() })

            val dialog: android.app.AlertDialog? = builder.create()
            dialog?.show()
        })
        return view
    }

    private fun checkEditText(view: View){
        if (TextUtils.isEmpty(view.et_mn.text.toString()) ||
            TextUtils.isEmpty(view.et_fn.text.toString()) ||
            TextUtils.isEmpty(view.et_bd.text.toString()) ||
            TextUtils.isEmpty(view.et_bo.text.toString()) ||
            TextUtils.isEmpty(view.ef_si.text.toString()) ||
            TextUtils.isEmpty(view.ts_em.text.toString()) ||
            TextUtils.isEmpty(view.et_mobile.text.toString()) ||
            rg.getCheckedRadioButtonId() == -1) {
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