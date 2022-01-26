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
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.pragmanila.awrasafely.R
import com.pragmanila.awrasafely.ui.preprequest.PrepRequestTabsActivity
import kotlinx.android.synthetic.main.message_box_pp.view.*
import kotlinx.android.synthetic.main.mode_of_request_fragment.*
import kotlinx.android.synthetic.main.mode_of_request_fragment.view.*

class ModeOfRequestFragment : Fragment(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    var checkedItem = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.prep_mode_of_request_fragment,container,false)

        view.et_tt.setOnClickListener(View.OnClickListener {
//            val listItems = arrayOf("Community Based Screening", "Schedule a visit at the Facility",
//                "Request Self-test Kit for Pick-up", "Request Self-test Kit for Delivery")
            val listItems = arrayOf("For Delivery", "Pickup at Facility",
                "Schedule a Visit at Facility")

            val builder: android.app.AlertDialog.Builder =
                android.app.AlertDialog.Builder(activity, R.style.MyAlertDialogTheme)
            builder.setTitle("Type of Test")

            builder.setSingleChoiceItems(listItems, checkedItem,
                DialogInterface.OnClickListener { dialog, which ->
                    et_tt.text = Editable.Factory.getInstance().newEditable(listItems[which])
                    if(which == 2) {
                        view.ll_sf.visibility = View.VISIBLE
                        view.prs_text.visibility = View.VISIBLE
                        view.ll_ps.visibility = View.VISIBLE

                        view.da_text.visibility = View.GONE
                        view.ll_da.visibility = View.GONE
                        view.mobile_text.visibility = View.GONE
                        view.mob.visibility = View.GONE
                        view.ll_rd.visibility = View.GONE
                        view.cl.visibility = View.GONE
                        checker1(view)
                        checkedItem = 0
                    }  else if (which == 0) {

                        view.prs_text.visibility =View.GONE
                        view.ll_ps.visibility = View.GONE
                        view.ll_sf.visibility = View.GONE
                        view.cl.visibility = View.GONE

                        view.da_text.visibility = View.VISIBLE
                        view.ll_da.visibility = View.VISIBLE
                        view.mobile_text.visibility = View.VISIBLE
                        view.mob.visibility = View.VISIBLE
                        view.ll_rd.visibility = View.VISIBLE
                        checker2(view)
                        checkedItem = 2

                    } else if (which == 1) {
                        view.prs_text.visibility =View.GONE
                        view.ll_ps.visibility = View.GONE
                        view.ll_sf.visibility = View.GONE
                        view.cl.visibility = View.GONE

                        view.da_text.visibility = View.VISIBLE
                        view.ll_da.visibility = View.VISIBLE
                        view.mobile_text.visibility = View.VISIBLE
                        view.mob.visibility = View.VISIBLE
                        view.ll_rd.visibility = View.VISIBLE
                        checker2(view)
                        checkedItem = 3

                    }
//                    Toast.makeText(
//                        activity,
//                        "Position: " + which + " Value: " + listItems[which],
//                        Toast.LENGTH_LONG
//                    ).show()
                })

            builder.setPositiveButton("Confirm",
                DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })

            val dialog: android.app.AlertDialog? = builder.create()
            dialog?.show()
        })


        view.et_bd.setOnClickListener(View.OnClickListener {
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
                    et_bd.text = Editable.Factory.getInstance().newEditable(bday)
                }, year, month, day)
            }
            datePickerDialog!!.getDatePicker().setMaxDate(System.currentTimeMillis());
            datePickerDialog!!.show()

        })

        view.tv_ts_ct_next.setOnClickListener(View.OnClickListener {
            (activity as PrepRequestTabsActivity).nextPage()
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragment_container, SummaryFragment())
            transaction?.disallowAddToBackStack()
            transaction?.commit()
        })

//        val radioGroup = view.findViewById<RadioGroup>(R.id.rg_rd)
        view.rg_rd.setOnCheckedChangeListener { group, checkedId ->
            if(R.id.radio_yes == checkedId){
                view.cl.visibility = View.VISIBLE
                rd_radio_yes.setBackgroundResource(R.drawable.rounded_rectangle_clicked)
                rd_radio_no.setBackgroundResource(R.drawable.rounded_rectangle_unclicked)
            } else if(R.id.radio_no == checkedId){
                view.cl.visibility = View.GONE
                rd_radio_no.setBackgroundResource(R.drawable.rounded_rectangle_clicked)
                rd_radio_yes.setBackgroundResource(R.drawable.rounded_rectangle_unclicked)
            }
//            var text = "You selected: "
//            text += if (R.id.radio_male == checkedId) "Male" else "Female"
//            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        }

        view.tv_pp.setOnClickListener(View.OnClickListener {
            val messageBoxView = LayoutInflater.from(activity).inflate(R.layout.message_box_pp, null)

            //AlertDialogBuilder
            val messageBoxBuilder = activity?.let { it1 -> AlertDialog.Builder(it1).setView(messageBoxView) }


            //show dialog
            val  messageBoxInstance = messageBoxBuilder?.show()

            //set Listener
            messageBoxView.done.setOnClickListener(){
                //close dialog
                messageBoxInstance?.dismiss()
            }
        })

        return view
    }

    private fun checkEditText1(view: View){
        if (TextUtils.isEmpty(view.et_pf.text.toString()) ||
            TextUtils.isEmpty(view.cf_fm.text.toString()) ||
            TextUtils.isEmpty(view.prf_nm.text.toString()) ||
            TextUtils.isEmpty(view.et_bd.text.toString()) ||
            TextUtils.isEmpty(view.et_tt.text.toString())||
            rg.getCheckedRadioButtonId() == -1||
            cb_pp.isChecked == false) {
            view.tv_ts_ct_next.setBackgroundResource(R.drawable.rounded_rectangle_blue_disabled)
            view.tv_ts_ct_next.isEnabled = false
            view.tv_ts_ct_next.isClickable = false
        } else {
            view.tv_ts_ct_next.setBackgroundResource(R.drawable.rounded_rectangle_blue_enabled)
            view.tv_ts_ct_next.isEnabled = true
            view.tv_ts_ct_next.isClickable = true
        }
    }

    private fun checkEditText2(view: View){
        if (TextUtils.isEmpty(view.et_pf.text.toString()) ||
            TextUtils.isEmpty(view.cf_fm.text.toString()) ||
            TextUtils.isEmpty(view.prf_nm.text.toString()) ||
            TextUtils.isEmpty(view.et_tt.text.toString())||
            TextUtils.isEmpty(view.et_da.text.toString())||
            TextUtils.isEmpty(view.et_mobile.text.toString())||
            rg_rd.getCheckedRadioButtonId() == -1||
            cb_pp.isChecked == false) {
            view.tv_ts_ct_next.setBackgroundResource(R.drawable.rounded_rectangle_blue_disabled)
            view.tv_ts_ct_next.isEnabled = false
            view.tv_ts_ct_next.isClickable = false
        } else {
            view.tv_ts_ct_next.setBackgroundResource(R.drawable.rounded_rectangle_blue_enabled)
            view.tv_ts_ct_next.isEnabled = true
            view.tv_ts_ct_next.isClickable = true
        }
    }

    private fun checker1(view: View){
        view.et_pf.doAfterTextChanged {
            checkEditText1(view)
        }
        view.cf_fm.doAfterTextChanged {
            checkEditText1(view)
        }
        view.prf_nm.doAfterTextChanged {
            checkEditText1(view)
        }
        view.et_bd.doAfterTextChanged {
            checkEditText1(view)
        }
        view.et_tt.doAfterTextChanged {
            checkEditText1(view)
        }
        rg.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->

            val radio: RadioButton = group.findViewById(checkedId)
//            Log.e("selectedtext-->",radio.text.toString())
            checkEditText1(view)

        })

        cb_pp.setOnCheckedChangeListener { buttonView, isChecked ->
            checkEditText1(view)
        }
    }

    private fun checker2(view: View){
        view.et_pf.doAfterTextChanged {
            checkEditText2(view)
        }
        view.cf_fm.doAfterTextChanged {
            checkEditText2(view)
        }
        view.prf_nm.doAfterTextChanged {
            checkEditText2(view)
        }
        view.et_tt.doAfterTextChanged {
            checkEditText2(view)
        }
        view.et_da.doAfterTextChanged {
            checkEditText2(view)
        }
        view.et_mobile.doAfterTextChanged {
            checkEditText2(view)
        }
        view.rg_rd.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->

            val radio: RadioButton = group.findViewById(checkedId)
//            Log.e("selectedtext-->",radio.text.toString())
            checkEditText2(view)

        })
        cb_pp.setOnCheckedChangeListener { buttonView, isChecked ->
            checkEditText2(view)
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