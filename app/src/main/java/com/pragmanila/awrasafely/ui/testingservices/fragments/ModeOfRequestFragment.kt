package com.pragmanila.awrasafely.ui.testingservices.fragments

import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.content.res.ColorStateList
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
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.pragmanila.awrasafely.R
import com.pragmanila.awrasafely.ui.testingservices.TestingServicesTabsActivity
import kotlinx.android.synthetic.main.message_box.view.*
import kotlinx.android.synthetic.main.message_box_pp.view.*
import kotlinx.android.synthetic.main.mode_of_request_fragment.*
import kotlinx.android.synthetic.main.mode_of_request_fragment.rg
import kotlinx.android.synthetic.main.mode_of_request_fragment.view.*
import kotlinx.android.synthetic.main.mode_of_request_fragment.view.tv_ts_ct_next
import androidx.constraintlayout.widget.ConstraintSet

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat.setBackgroundTintList
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.pragmanila.awrasafely.MainViewModel
import com.pragmanila.awrasafely.api.model.UserResponse
import com.pragmanila.awrasafely.ui.testingservices.ApiValidationFragment
import kotlinx.android.synthetic.main.mode_of_request_fragment.et_bd
import kotlinx.android.synthetic.main.mode_of_request_fragment.view.et_bd
import kotlinx.android.synthetic.main.mode_of_request_fragment.view.et_mobile
import kotlinx.android.synthetic.main.mode_of_request_fragment.view.mob
import kotlinx.android.synthetic.main.mode_of_request_fragment.view.mobile_text
import kotlinx.android.synthetic.main.new_personal_information_fragment.*
import kotlinx.android.synthetic.main.new_personal_information_fragment.view.*
import kotlinx.android.synthetic.main.risk_assessment_fragment.view.*
import java.lang.Thread.sleep
import java.util.regex.Pattern


class ModeOfRequestFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    private fun mobilePattern(text: String?): Boolean {
        var p = Pattern.compile("[9][0-9]{9}")
        var m = p.matcher(text)
        return m.matches()
    }

    var numlb = 0
    var num = 0
    var checkedItem = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.mode_of_request_fragment, container, false)


        view.tv_ts_ct_next.setOnClickListener {
            checkEditText(view)
            if (checkEditText(view)) {

                //passing data to summary
                val mobile = view.et_mobile
                val mobileInput = mobile.text.toString()
                val bundle = Bundle()
                bundle.putString("mobile", mobileInput)
                val fragment = SummaryFragment()
                fragment.arguments = bundle
//                val transaction = activity?.supportFragmentManager?.beginTransaction()
//                transaction?.replace(R.id.fragment_container, SummaryFragment(), "MY_FRAGMENT")
//                transaction?.addToBackStack("MY_FRAGMENT")
//                transaction?.commit()

                var userInfo = viewModel.userInfo.value
                Log.e("junior", "getUserInfo : " + userInfo)
                if (userInfo != null){
                    viewModel.createNewUser(userInfo)
                    //await 1 s
                    sleep(1000)

                    viewModel.createNewUserLiveData.observe(activity as TestingServicesTabsActivity,
                        androidx.lifecycle.Observer<UserResponse?> {
                            if(it  != null) {
                                //{"code":201,"meta":null,"data":{"id":2877,"name":"xxxxxaaaaabbbbb","email":"xxxxxaaaaabbbbb@gmail.com","gender":"male","status":"active"}}
                                (activity as TestingServicesTabsActivity).nextPage()
                            }
                        })
                }
            } else {
                checkEditText(view)
            }
        }

        view.et_pf_region.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {

                when {
                    view.et_pf_region.text.toString().isEmpty() -> {
                        // Not allowed
                        view.pr_fa_ti_rg.error = "This field is required"
                    }
                    else -> {
                        view.pr_fa_ti_rg.error = null
                        view.pr_fa_ti_rg.isErrorEnabled = false
                        view.et_pf_region.setBackgroundResource(R.drawable.textbox_unclicked)
                    }
                }
            } else if (hasFocus) {
                view.et_pf_region.setBackgroundResource(R.drawable.textbox_clicked)
            }
        }
        val provinceList1 = listOf("Option 1", "Option 2", "Option 3", "Option 4")
        val pAdapter1 = ArrayAdapter(requireContext(), R.layout.province_facility, provinceList1)
        view.et_pf_region.setAdapter(pAdapter1)

        //dropdown list
        view.et_pf.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {

                when {
                    view.et_pf.text.toString().isEmpty() -> {
                        // Not allowed
                        view.pr_fa_ti.error = "This field is required"
                    }
                    else -> {
                        view.pr_fa_ti.error = null
                        view.pr_fa_ti.isErrorEnabled = false
                        view.et_pf.setBackgroundResource(R.drawable.textbox_unclicked)
                    }
                }
            } else if (hasFocus) {
                view.et_pf.setBackgroundResource(R.drawable.textbox_clicked)
            }
        }
        val provinceList = listOf("Option 1", "Option 2", "Option 3", "Option 4")
        val pAdapter = ArrayAdapter(requireContext(), R.layout.province_facility, provinceList)
        view.et_pf.setAdapter(pAdapter)


        view.cf_fm.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {

                when {
                    view.cf_fm.text.toString().isEmpty() -> {
                        // Not allowed
                        view.ct_fa_ti.error = "This field is required"
                        view.cf_fm.setBackgroundResource(R.drawable.textbox_error_bg)
                    }
                    else -> {
                        view.ct_fa_ti.error = null
                        view.ct_fa_ti.isErrorEnabled = false
                        view.cf_fm.setBackgroundResource(R.drawable.textbox_unclicked)
                    }
                }
            } else if (hasFocus) {
                view.cf_fm.setBackgroundResource(R.drawable.textbox_clicked)
            }
        }
        val cityList = listOf("Option 1", "Option 2", "Option 3", "Option 4")
        val cAdapter = ArrayAdapter(requireContext(), R.layout.province_facility, cityList)
        view.cf_fm.setAdapter(cAdapter)

        view.prf_nm.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                when {
                    view.prf_nm.text.toString().isEmpty() -> {
                        // Not allowed
                        view.prf_fa_ti.error = "This field is required"
                        view.prf_nm.setBackgroundResource(R.drawable.textbox_error_bg)
                    }
                    else -> {
                        view.prf_fa_ti.error = null
                        view.prf_fa_ti.isErrorEnabled = false
                        view.prf_nm.setBackgroundResource(R.drawable.textbox_unclicked)
                    }
                }
            } else if (hasFocus) {
                view.prf_nm.setBackgroundResource(R.drawable.textbox_clicked)
            }
        }
        val prfList = listOf("Option 1", "Option 2", "Option 3", "Option 4")
        val prfAdapter = ArrayAdapter(requireContext(), R.layout.province_facility, prfList)
        view.prf_nm.setAdapter(prfAdapter)




        view.et_tt.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                when {
                    view.et_tt.text.toString().isEmpty() -> {
                        // Not allowed
                        view.et_tt.error = "This field is required"
                        view.et_tt.setBackgroundResource(R.drawable.textbox_error_bg)
                    }
                    else -> {
                        view.et_tt.error = null
                        view.et_tt.setBackgroundResource(R.drawable.textbox_unclicked)
                    }
                }
            } else if (hasFocus) {
                if (et_tt.error == null) {
                    view.et_tt.setBackgroundResource(R.drawable.textbox_clicked)
                    val listItems = arrayOf(
                        "Schedule a visit at the Facility", "Request CBS Motivator",
                        "Request Self-test Kit for Pick-up", "Request Self-test Kit for Delivery"
                    )

                    val builder: android.app.AlertDialog.Builder =
                        android.app.AlertDialog.Builder(activity, R.style.MyAlertDialogTheme)
                    builder.setTitle("Type of Test")

                    builder.setSingleChoiceItems(listItems, checkedItem,
                        DialogInterface.OnClickListener { dialog, which ->
                            et_tt.text =
                                Editable.Factory.getInstance().newEditable(listItems[which])
                            if (which == 0) {
                                view.ll_sf.visibility = View.VISIBLE
                                view.prs_text.visibility = View.VISIBLE
                                view.ll_ps.visibility = View.VISIBLE
                                rg.visibility = View.VISIBLE
                                view.da_text.visibility = View.GONE
                                view.ll_da.visibility = View.GONE
                                view.mobile_text.visibility = View.GONE
                                view.mob.visibility = View.GONE
                                view.ll_rd.visibility = View.GONE
                                view.rg_rd.visibility = View.GONE
                                view.cl.visibility=View.GONE



//                                    checker1(view)
                                checkedItem = 0
                            } else if (which == 1) {
                                view.ll_sf.visibility = View.GONE
                                view.prs_text.visibility = View.VISIBLE
                                view.ll_ps.visibility = View.VISIBLE


                                view.da_text.visibility = View.GONE
                                view.ll_da.visibility = View.GONE
                                view.mobile_text.visibility = View.GONE
                                view.mob.visibility = View.GONE
                                view.ll_rd.visibility = View.GONE
                                view.rg_rd.visibility = View.GONE
                                view.cl.visibility=View.GONE
                                rg.visibility = View.GONE


//                                    checker1(view)

                                checkedItem = 1
                            } else if (which == 2) {
                                val constraintLayout: ConstraintLayout = view.mode_layout
                                val constraintSet = ConstraintSet()
                                constraintSet.clone(constraintLayout)
                                constraintSet.connect(
                                    R.id.ll_rd,
                                    ConstraintSet.START,
                                    R.id.ll_ps,
                                    ConstraintSet.START,

                                    )
                                constraintSet.connect(
                                    R.id.ll_rd,
                                    ConstraintSet.TOP,
                                    R.id.ll_ps,
                                    ConstraintSet.BOTTOM,
                                    10
                                )
                                constraintSet.applyTo(constraintLayout)

                                view.prs_text.visibility = View.VISIBLE
                                view.ll_ps.visibility = View.VISIBLE
                                view.ll_sf.visibility = View.GONE


                                view.da_text.visibility = View.GONE
                                view.ll_da.visibility = View.GONE
                                view.mobile_text.visibility = View.GONE
                                view.mob.visibility = View.GONE
                                rg.visibility = View.GONE
                                view.cl.visibility = View.GONE
                                view.ll_rd.visibility = View.VISIBLE
                                view.rg_rd.visibility = View.VISIBLE

                                if(view.rd_radio_yes.isChecked==true){
                                    view.cl.visibility = View.VISIBLE
                                }else if(view.rd_radio_no.isChecked==true){
                                    view.cl.visibility = View.GONE
                                }else{
                                    view.cl.visibility = View.GONE
                                }

//                                    checker2(view)
                                checkedItem = 2

                                //condoms and lubricants
                                button1.setOnClickListener {
                                    num--
                                    nm_cd.text = num.toString()
                                    if (num <= 0) {
                                        button1.setBackgroundColor(resources.getColor(R.color.disabled))
                                        button1.isEnabled = false
                                        button2.setBackgroundColor(resources.getColor(R.color.blue))
                                        button2.isEnabled = true
                                    } else if (num >= 1 && num < 10) {
                                        button1.setBackgroundColor(resources.getColor(R.color.blue))
                                        button1.isEnabled = true
                                        button2.setBackgroundColor(resources.getColor(R.color.blue))
                                        button2.isEnabled = true
                                    } else {
                                        button1.setBackgroundColor(resources.getColor(R.color.blue))
                                        button1.isEnabled = true
                                    }
                                }
                                button2.setOnClickListener {
                                    num++
                                    nm_cd.text = num.toString()
                                    if (num >= 10) {
                                        button2.setBackgroundColor(resources.getColor(R.color.disabled))
                                        button2.isEnabled = false
                                        button1.setBackgroundColor(resources.getColor(R.color.blue))
                                        button1.isEnabled = true
                                    } else if (num >= 1 && num < 10) {
                                        button1.setBackgroundColor(resources.getColor(R.color.blue))
                                        button1.isEnabled = true
                                        button2.setBackgroundColor(resources.getColor(R.color.blue))
                                        button2.isEnabled = true
                                    } else {
                                        button2.setBackgroundColor(resources.getColor(R.color.blue))
                                        button2.isEnabled = true
                                    }

                                }

                                button1_lb.setOnClickListener {
                                    numlb--
                                    nm_lb.text = numlb.toString()
                                    if (numlb <= 0) {
                                        button1_lb.setBackgroundColor(resources.getColor(R.color.disabled))
                                        button1_lb.isEnabled = false
                                        button2_lb.setBackgroundColor(resources.getColor(R.color.blue))
                                        button2_lb.isEnabled = true
                                    } else if (numlb >= 1 && numlb < 10) {
                                        button1_lb.setBackgroundColor(resources.getColor(R.color.blue))
                                        button1_lb.isEnabled = true
                                        button2_lb.setBackgroundColor(resources.getColor(R.color.blue))
                                        button2_lb.isEnabled = true
                                    } else {
                                        button1_lb.setBackgroundColor(resources.getColor(R.color.blue))
                                        button1_lb.isEnabled = true
                                    }
                                }
                                button2_lb.setOnClickListener {
                                    numlb++
                                    nm_lb.text = numlb.toString()

                                    if (numlb >= 10) {
                                        button2_lb.setBackgroundColor(resources.getColor(R.color.disabled))
                                        button2_lb.isEnabled = false
                                        button1_lb.setBackgroundColor(resources.getColor(R.color.blue))
                                        button1_lb.isEnabled = true
                                    } else if (numlb >= 1 && numlb < 10) {
                                        button1_lb.setBackgroundColor(resources.getColor(R.color.blue))
                                        button1_lb.isEnabled = true
                                        button2_lb.setBackgroundColor(resources.getColor(R.color.blue))
                                        button2_lb.isEnabled = true
                                    } else {
                                        button2_lb.setBackgroundColor(resources.getColor(R.color.blue))
                                        button2_lb.isEnabled = true
                                    }
                                }
                            } else if (which == 3) {

                                val constraintLayout: ConstraintLayout = view.mode_layout
                                val constraintSet = ConstraintSet()
                                constraintSet.clone(constraintLayout)
                                constraintSet.connect(
                                    R.id.ll_rd,
                                    ConstraintSet.START,
                                    R.id.mob,
                                    ConstraintSet.START,

                                    )
                                constraintSet.connect(
                                    R.id.ll_rd,
                                    ConstraintSet.TOP,
                                    R.id.mob,
                                    ConstraintSet.BOTTOM,
                                    10
                                )
                                constraintSet.applyTo(constraintLayout)

                                view.prs_text.visibility = View.GONE
                                view.ll_ps.visibility = View.GONE
                                view.ll_sf.visibility = View.GONE
                                rg.visibility = View.GONE
                                view.cl.visibility = View.GONE


                                view.da_text.visibility = View.VISIBLE
                                view.ll_da.visibility = View.VISIBLE
                                view.mobile_text.visibility = View.VISIBLE
                                view.mob.visibility = View.VISIBLE
                                view.ll_rd.visibility = View.VISIBLE
                                view.rg_rd.visibility = View.VISIBLE

                                if(view.rd_radio_yes.isChecked==true){
                                    view.cl.visibility = View.VISIBLE
                                }else if(view.rd_radio_no.isChecked==true){
                                    view.cl.visibility = View.GONE
                                }else{
                                    view.cl.visibility = View.GONE
                                }


//                                    checker2(view)
                                checkedItem = 3
                                //condoms and lubricants
                                button1.setOnClickListener {
                                    num--
                                    nm_cd.text = num.toString()
                                    if (num <= 0) {
                                        button1.setBackgroundColor(resources.getColor(R.color.disabled))
                                        button1.isEnabled = false
                                        button2.setBackgroundColor(resources.getColor(R.color.blue))
                                        button2.isEnabled = true
                                    } else if (num >= 1 && num < 10) {
                                        button1.setBackgroundColor(resources.getColor(R.color.blue))
                                        button1.isEnabled = true
                                        button2.setBackgroundColor(resources.getColor(R.color.blue))
                                        button2.isEnabled = true
                                    } else {
                                        button1.setBackgroundColor(resources.getColor(R.color.blue))
                                        button1.isEnabled = true
                                    }
                                }
                                button2.setOnClickListener {
                                    num++
                                    nm_cd.text = num.toString()
                                    if (num >= 10) {
                                        button2.setBackgroundColor(resources.getColor(R.color.disabled))
                                        button2.isEnabled = false
                                        button1.setBackgroundColor(resources.getColor(R.color.blue))
                                        button1.isEnabled = true
                                    } else if (num >= 1 && num < 10) {
                                        button1.setBackgroundColor(resources.getColor(R.color.blue))
                                        button1.isEnabled = true
                                        button2.setBackgroundColor(resources.getColor(R.color.blue))
                                        button2.isEnabled = true
                                    } else {
                                        button2.setBackgroundColor(resources.getColor(R.color.blue))
                                        button2.isEnabled = true
                                    }

                                }

                                button1_lb.setOnClickListener {
                                    numlb--
                                    nm_lb.text = numlb.toString()
                                    if (numlb <= 0) {
                                        button1_lb.setBackgroundColor(resources.getColor(R.color.disabled))
                                        button1_lb.isEnabled = false
                                        button2_lb.setBackgroundColor(resources.getColor(R.color.blue))
                                        button2_lb.isEnabled = true
                                    } else if (numlb >= 1 && numlb < 10) {
                                        button1_lb.setBackgroundColor(resources.getColor(R.color.blue))
                                        button1_lb.isEnabled = true
                                        button2_lb.setBackgroundColor(resources.getColor(R.color.blue))
                                        button2_lb.isEnabled = true
                                    } else {
                                        button1_lb.setBackgroundColor(resources.getColor(R.color.blue))
                                        button1_lb.isEnabled = true
                                    }
                                }
                                button2_lb.setOnClickListener {
                                    numlb++
                                    nm_lb.text = numlb.toString()
                                    if (numlb >= 10) {
                                        button2_lb.setBackgroundColor(resources.getColor(R.color.disabled))
                                        button2_lb.isEnabled = false
                                        button1_lb.setBackgroundColor(resources.getColor(R.color.blue))
                                        button1_lb.isEnabled = true
                                    } else if (numlb >= 1 && numlb < 10) {
                                        button1_lb.setBackgroundColor(resources.getColor(R.color.blue))
                                        button1_lb.isEnabled = true
                                        button2_lb.setBackgroundColor(resources.getColor(R.color.blue))
                                        button2_lb.isEnabled = true
                                    } else {
                                        button2_lb.setBackgroundColor(resources.getColor(R.color.blue))
                                        button2_lb.isEnabled = true
                                    }

                                }

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
                } else {
                    view.et_tt.setBackgroundResource(R.drawable.textbox_error_bg)
                    view.et_tt.error = "This field is required"
                }

            }
        }
        view.et_tt.setOnClickListener(View.OnClickListener {
            view.et_tt.setBackgroundResource(R.drawable.textbox_clicked)
            val listItems = arrayOf(
                "Schedule a visit at the Facility", "Request CBS Motivator",
                "Request Self-test Kit for Pick-up", "Request Self-test Kit for Delivery"
            )

            val builder: android.app.AlertDialog.Builder =
                android.app.AlertDialog.Builder(activity, R.style.MyAlertDialogTheme)
            builder.setTitle("Type of Test")

            builder.setSingleChoiceItems(listItems, checkedItem,
                DialogInterface.OnClickListener { dialog, which ->
                    et_tt.text = Editable.Factory.getInstance().newEditable(listItems[which])
                    if (which == 0) {
                        view.ll_sf.visibility = View.VISIBLE
                        view.prs_text.visibility = View.VISIBLE
                        view.ll_ps.visibility = View.VISIBLE
                        rg.visibility = View.VISIBLE


                        view.da_text.visibility = View.GONE
                        view.ll_da.visibility = View.GONE
                        view.mobile_text.visibility = View.GONE
                        view.mob.visibility = View.GONE
                        view.ll_rd.visibility = View.GONE
                        view.cl.visibility = View.GONE
                        view.rg_rd.visibility = View.GONE



//                        checker1(view)
                        checkedItem = 0
                    } else if (which == 1) {
                        view.ll_sf.visibility = View.GONE
                        view.prs_text.visibility = View.VISIBLE
                        view.ll_ps.visibility = View.VISIBLE
                        view.cl.visibility = View.GONE


                        view.da_text.visibility = View.GONE
                        view.ll_da.visibility = View.GONE
                        view.mobile_text.visibility = View.GONE
                        view.mob.visibility = View.GONE
                        view.ll_rd.visibility = View.GONE
                        view.rg_rd.visibility = View.GONE
                        rg.visibility = View.GONE



//                        checker1(view)
                        checkedItem = 1
                    } else if (which == 2) {

                        val constraintLayout: ConstraintLayout = view.mode_layout
                        val constraintSet = ConstraintSet()
                        constraintSet.clone(constraintLayout)
                        constraintSet.connect(
                            R.id.ll_rd,
                            ConstraintSet.START,
                            R.id.ll_ps,
                            ConstraintSet.START,
                        )
                        constraintSet.connect(
                            R.id.ll_rd,
                            ConstraintSet.TOP,
                            R.id.ll_ps,
                            ConstraintSet.BOTTOM,
                            10
                        )
                        constraintSet.applyTo(constraintLayout)

                        view.prs_text.visibility = View.VISIBLE
                        view.ll_ps.visibility = View.VISIBLE
                        view.ll_sf.visibility = View.GONE


                        view.da_text.visibility = View.GONE
                        view.ll_da.visibility = View.GONE
                        view.mobile_text.visibility = View.GONE
                        view.mob.visibility = View.GONE
                        view.ll_rd.visibility = View.VISIBLE
                        view.rg_rd.visibility = View.VISIBLE
                        view.et_da.visibility = View.GONE
                        view.et_mobile.visibility = View.GONE
                        rg.visibility = View.GONE

                        if(view.rd_radio_yes.isChecked==true){
                            view.cl.visibility = View.VISIBLE
                        }else if(view.rd_radio_no.isChecked==true){
                            view.cl.visibility = View.GONE
                        }else{
                            view.cl.visibility = View.GONE
                        }


//                        checker2(view)
                        checkedItem = 2


                        //condoms and lubricants
                        button1.setOnClickListener {
                            num--
                            nm_cd.text = num.toString()
                            if (num <= 0) {
                                button1.setBackgroundColor(resources.getColor(R.color.disabled))
                                button1.isEnabled = false
                                button2.setBackgroundColor(resources.getColor(R.color.blue))
                                button2.isEnabled = true
                            } else if (num >= 1 && num < 10) {
                                button1.setBackgroundColor(resources.getColor(R.color.blue))
                                button1.isEnabled = true
                                button2.setBackgroundColor(resources.getColor(R.color.blue))
                                button2.isEnabled = true
                            } else {
                                button1.setBackgroundColor(resources.getColor(R.color.blue))
                                button1.isEnabled = true
                            }
                        }
                        button2.setOnClickListener {
                            num++
                            nm_cd.text = num.toString()
                            if (num >= 10) {
                                button2.setBackgroundColor(resources.getColor(R.color.disabled))
                                button2.isEnabled = false
                                button1.setBackgroundColor(resources.getColor(R.color.blue))
                                button1.isEnabled = true
                            } else if (num >= 1 && num < 10) {
                                button1.setBackgroundColor(resources.getColor(R.color.blue))
                                button1.isEnabled = true
                                button2.setBackgroundColor(resources.getColor(R.color.blue))
                                button2.isEnabled = true
                            } else {
                                button2.setBackgroundColor(resources.getColor(R.color.blue))
                                button2.isEnabled = true
                            }

                        }

                        button1_lb.setOnClickListener {
                            numlb--
                            nm_lb.text = numlb.toString()

                             if (numlb <= 0) {
                                button1_lb.setBackgroundColor(resources.getColor(R.color.disabled))
                                button1_lb.isEnabled = false
                                button2_lb.setBackgroundColor(resources.getColor(R.color.blue))
                                button2_lb.isEnabled = true
                            } else if (numlb >= 1 && numlb < 10) {
                                button1_lb.setBackgroundColor(resources.getColor(R.color.blue))
                                button1_lb.isEnabled = true
                                button2_lb.setBackgroundColor(resources.getColor(R.color.blue))
                                button2_lb.isEnabled = true
                            } else {
                                button1_lb.setBackgroundColor(resources.getColor(R.color.blue))
                                button1_lb.isEnabled = true
                            }
                        }
                        button2_lb.setOnClickListener {
                            numlb++
                            nm_lb.text = numlb.toString()
                            if (numlb >= 10) {
                                button2_lb.setBackgroundColor(resources.getColor(R.color.disabled))
                                button2_lb.isEnabled = false
                                button1_lb.setBackgroundColor(resources.getColor(R.color.blue))
                                button1_lb.isEnabled = true
                            } else if (numlb >= 1 && numlb < 10) {
                                button1_lb.setBackgroundColor(resources.getColor(R.color.blue))
                                button1_lb.isEnabled = true
                                button2_lb.setBackgroundColor(resources.getColor(R.color.blue))
                                button2_lb.isEnabled = true
                            } else {
                                button2_lb.setBackgroundColor(resources.getColor(R.color.blue))
                                button2_lb.isEnabled = true
                            }
                        }


                    } else if (which == 3) {

                        val constraintLayout: ConstraintLayout = view.mode_layout
                        val constraintSet = ConstraintSet()
                        constraintSet.clone(constraintLayout)
                        constraintSet.connect(
                            R.id.ll_rd,
                            ConstraintSet.START,
                            R.id.mob,
                            ConstraintSet.START,

                            )
                        constraintSet.connect(
                            R.id.ll_rd,
                            ConstraintSet.TOP,
                            R.id.mob,
                            ConstraintSet.BOTTOM,
                            10
                        )
                        constraintSet.applyTo(constraintLayout)


                        view.prs_text.visibility = View.GONE
                        view.ll_ps.visibility = View.GONE
                        view.ll_sf.visibility = View.GONE


                        view.da_text.visibility = View.VISIBLE
                        view.ll_da.visibility = View.VISIBLE
                        view.et_da.visibility = View.VISIBLE
                        view.mobile_text.visibility = View.VISIBLE
                        view.et_mobile.visibility = View.VISIBLE
                        view.mob.visibility = View.VISIBLE
                        view.ll_rd.visibility = View.VISIBLE
                        view.rg_rd.visibility = View.VISIBLE
                        rg.visibility = View.GONE

                         if(view.rd_radio_yes.isChecked==true){
                            view.cl.visibility = View.VISIBLE
                        }else if(view.rd_radio_no.isChecked==true){
                            view.cl.visibility = View.GONE
                        }else{
                            view.cl.visibility = View.GONE
                        }


                        checkedItem = 3

                        //condoms and lubricants
                        button1.setOnClickListener {
                            num--
                            nm_cd.text = num.toString()
                            if (num <= 0) {
                                button1.setBackgroundColor(resources.getColor(R.color.disabled))
                                button1.isEnabled = false
                                button2.setBackgroundColor(resources.getColor(R.color.blue))
                                button2.isEnabled = true
                            } else if (num >= 1 && num < 10) {
                                button1.setBackgroundColor(resources.getColor(R.color.blue))
                                button1.isEnabled = true
                                button2.setBackgroundColor(resources.getColor(R.color.blue))
                                button2.isEnabled = true
                            } else {
                                button1.setBackgroundColor(resources.getColor(R.color.blue))
                                button1.isEnabled = true
                            }
                        }
                        button2.setOnClickListener {
                            num++
                            nm_cd.text = num.toString()
                            if (num >= 10) {
                                button2.setBackgroundColor(resources.getColor(R.color.disabled))
                                button2.isEnabled = false
                                button1.setBackgroundColor(resources.getColor(R.color.blue))
                                button1.isEnabled = true
                            } else if (num >= 1 && num < 10) {
                                button1.setBackgroundColor(resources.getColor(R.color.blue))
                                button1.isEnabled = true
                                button2.setBackgroundColor(resources.getColor(R.color.blue))
                                button2.isEnabled = true
                            } else {
                                button2.setBackgroundColor(resources.getColor(R.color.blue))
                                button2.isEnabled = true
                            }

                        }

                        button1_lb.setOnClickListener {
                            numlb--
                            nm_lb.text = numlb.toString()

                            if (numlb <= 0) {
                                button1_lb.setBackgroundColor(resources.getColor(R.color.disabled))
                                button1_lb.isEnabled = false
                                button2_lb.setBackgroundColor(resources.getColor(R.color.blue))
                                button2_lb.isEnabled = true
                            } else if (numlb >= 1 && numlb < 10) {
                                button1_lb.setBackgroundColor(resources.getColor(R.color.blue))
                                button1_lb.isEnabled = true
                                button2_lb.setBackgroundColor(resources.getColor(R.color.blue))
                                button2_lb.isEnabled = true
                            } else {
                                button1_lb.setBackgroundColor(resources.getColor(R.color.blue))
                                button1_lb.isEnabled = true
                            }
                        }
                        button2_lb.setOnClickListener {
                            numlb++
                            nm_lb.text = numlb.toString()
                            if (numlb >= 10) {
                                button2_lb.setBackgroundColor(resources.getColor(R.color.disabled))
                                button2_lb.isEnabled = false
                                button1_lb.setBackgroundColor(resources.getColor(R.color.blue))
                                button1_lb.isEnabled = true
                            } else if (numlb >= 1 && numlb < 10) {
                                button1_lb.setBackgroundColor(resources.getColor(R.color.blue))
                                button1_lb.isEnabled = true
                                button2_lb.setBackgroundColor(resources.getColor(R.color.blue))
                                button2_lb.isEnabled = true
                            } else {
                                button2_lb.setBackgroundColor(resources.getColor(R.color.blue))
                                button2_lb.isEnabled = true
                            }
                        }


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


        view.et_bd.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                when {
                    view.et_bd.text.toString().isEmpty() -> {
                        // Not allowed
                        view.et_bd.error = "This field is required"
                        view.ll_ps.setBackgroundResource(R.drawable.textbox_error_bg)
                    }
                    else -> {
                        view.et_bd.error = null
                        view.ll_ps.setBackgroundResource(R.drawable.textbox_unclicked)
                    }
                }
            } else if (hasFocus) {
                view.ll_ps.setBackgroundResource(R.drawable.textbox_clicked)
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
                        it1, DatePickerDialog.OnDateSetListener
                        { view, year, monthOfYear, dayOfMonth ->
                            Log.e(
                                "DATE>>>>",
                                "" + (monthOfYear + 1) + " / " + dayOfMonth + " / " + year
                            )
                            var bday = "" + (monthOfYear + 1) + " / " + dayOfMonth + " / " + year
                            et_bd.text = Editable.Factory.getInstance().newEditable(bday)
                        }, year, month, day
                    )
                }
                datePickerDialog!!.datePicker.minDate = System.currentTimeMillis() - 1000
                datePickerDialog.show()
            }
        }

        view.et_bd.setOnClickListener(View.OnClickListener {
            view.ll_ps.setBackgroundResource(R.drawable.textbox_clicked)
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
                    it1, DatePickerDialog.OnDateSetListener
                    { view, year, monthOfYear, dayOfMonth ->
                        Log.e(
                            "DATE>>>>",
                            "" + (monthOfYear + 1) + " / " + dayOfMonth + " / " + year
                        )
                        var bday = "" + (monthOfYear + 1) + " / " + dayOfMonth + " / " + year
                        et_bd.text = Editable.Factory.getInstance().newEditable(bday)
                    }, year, month, day
                )
            }
            datePickerDialog!!.datePicker.minDate = System.currentTimeMillis() - 1000
            datePickerDialog.show()

        })

        view.sti_radio_yes.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                view.sti_radio_yes.setBackgroundResource(R.drawable.textbox_unclicked)
            } else if (hasFocus) {
                view.sti_radio_yes.isChecked = true
                view.sti_radio_no.error = null
                view.sti_radio_yes.setBackgroundResource(R.drawable.textbox_clicked)
                view.sti_radio_no.isChecked = false
                view.sti_radio_no.setBackgroundResource(R.drawable.textbox_unclicked)
            }
        }
        view.sti_radio_no.setOnClickListener {
            view.sti_radio_yes.isChecked = false
            view.sti_radio_yes.setBackgroundResource(R.drawable.textbox_unclicked)
            view.sti_radio_no.setBackgroundResource(R.drawable.textbox_clicked)
            view.sti_radio_no.isChecked = true
            view.sti_radio_no.error = null
        }
        view.sti_radio_no.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                if (rg.checkedRadioButtonId == -1 && rg.visibility == View.VISIBLE) {
                    view.sti_radio_no.setBackgroundResource(R.drawable.textbox_error_bg)
                    view.sti_radio_no.error = "This field is required"
                } else {
                    view.sti_radio_no.setBackgroundResource(R.drawable.textbox_unclicked)
                    view.sti_radio_no.error = null
                }
            } else if (hasFocus) {
                if (view.sti_radio_no.error != null) {
                    view.sti_radio_no.setBackgroundResource(R.drawable.textbox_error_bg)
                    view.sti_radio_yes.setBackgroundResource(R.drawable.textbox_error_bg)
                    view.sti_radio_no.isChecked = false
                    view.sti_radio_yes.isChecked = false
                } else if (view.sti_radio_no.error == null) {
                    view.sti_radio_no.setBackgroundResource(R.drawable.textbox_clicked)
                    view.sti_radio_yes.setBackgroundResource(R.drawable.textbox_unclicked)
                    view.sti_radio_yes.isChecked = false
                    view.sti_radio_no.isChecked = true
                } else {
                    view.sti_radio_no.setBackgroundResource(R.drawable.textbox_clicked)
                    view.sti_radio_no.isChecked = true
                    view.sti_radio_yes.setBackgroundResource(R.drawable.textbox_unclicked)
                    view.sti_radio_yes.isChecked = false
                }


            }
        }

        view.rd_radio_yes.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                view.rd_radio_yes.setBackgroundResource(R.drawable.textbox_unclicked)
            } else if (hasFocus) {
                view.rd_radio_yes.isChecked = true
                view.rd_radio_yes.setBackgroundResource(R.drawable.textbox_clicked)
                view.rd_radio_no.isChecked = false
                view.rd_radio_no.error = null
                view.rd_radio_no.setBackgroundResource(R.drawable.textbox_unclicked)
                view.cl.visibility = View.VISIBLE
            }
        }

        view.rd_radio_yes.setOnClickListener {
            view.rd_radio_yes.isChecked = true
            view.rd_radio_yes.setBackgroundResource(R.drawable.textbox_clicked)
            view.rd_radio_no.setBackgroundResource(R.drawable.textbox_unclicked)
            view.rd_radio_no.isChecked = false
            view.cl.visibility = View.VISIBLE
            view.rd_radio_no.error = null
        }

        view.rd_radio_no.setOnClickListener {
            view.rd_radio_yes.isChecked = false
            view.rd_radio_yes.setBackgroundResource(R.drawable.textbox_unclicked)
            view.rd_radio_no.setBackgroundResource(R.drawable.textbox_clicked)
            view.rd_radio_no.isChecked = true
            view.rd_radio_no.error = null
        }
        view.rd_radio_no.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                if (rg_rd.checkedRadioButtonId == -1 && rg_rd.visibility == View.VISIBLE) {
                    view.rd_radio_no.setBackgroundResource(R.drawable.textbox_unclicked)
                    view.rd_radio_no.error = "This field is required"
                } else {
                    view.rd_radio_no.setBackgroundResource(R.drawable.textbox_unclicked)
                    view.rd_radio_no.error = null
                }
            } else if (hasFocus) {
                if (view.rd_radio_no.error != null) {
                    view.rd_radio_no.isChecked = false
                    view.rd_radio_no.setBackgroundResource(R.drawable.textbox_error_bg)
                    view.rd_radio_yes.setBackgroundResource(R.drawable.textbox_error_bg)
                    view.rd_radio_yes.isChecked = false
                } else if (view.rd_radio_no.error == null) {
                    view.rd_radio_yes.isChecked = false
                    view.rd_radio_no.setBackgroundResource(R.drawable.textbox_clicked)
                    view.rd_radio_no.isChecked = true
                    view.cl.visibility = View.GONE
                }
            }
        }

        view.tv_pp.setOnClickListener(View.OnClickListener {
            val messageBoxView =
                LayoutInflater.from(activity).inflate(R.layout.message_box_pp, null)

            //AlertDialogBuilder
            val messageBoxBuilder =
                activity?.let { it1 -> AlertDialog.Builder(it1).setView(messageBoxView) }


            //show dialog
            val messageBoxInstance = messageBoxBuilder?.show()

            //set Listener
            messageBoxView.done.setOnClickListener {
                //close dialog
                messageBoxInstance?.dismiss()
            }
        })


        //condom and lubricants numberpicker


        view.cb_pp.setOnClickListener {
            view.tv_pp.setTextColor(Color.parseColor("#2E3192"))
            view.tv_pp.error = null
            view.cb_pp.buttonTintList = resources.getColorStateList(R.color.blue)

        }

        return view
    }


    private fun checkEditText(view: View): Boolean {
        if (TextUtils.isEmpty(view.et_pf_region.text.toString())) {
            view.pr_fa_ti_rg.error = "This field is required"
            view.et_pf_region.requestFocus()
            view.et_pf_region.setBackgroundResource(R.drawable.textbox_error_bg)
        }
       else if (TextUtils.isEmpty(view.et_pf.text.toString())) {
            view.pr_fa_ti.error = "This field is required"
            view.et_pf.requestFocus()
            view.et_pf.setBackgroundResource(R.drawable.textbox_error_bg)
        } else if (TextUtils.isEmpty(view.cf_fm.text.toString())) {
            view.cf_fm.setBackgroundResource(R.drawable.textbox_error_bg)
            view.cf_fm.requestFocus()
            view.ct_fa_ti.error = "This field is required"
        } else if (TextUtils.isEmpty(view.prf_nm.text.toString())) {
            view.prf_fa_ti.error = "This field is required"
            view.prf_nm.requestFocus()
            view.prf_nm.setBackgroundResource(R.drawable.textbox_error_bg)
        } else if (TextUtils.isEmpty(view.et_tt.text.toString())) {
            view.et_tt.error = "This field is required"
            view.et_tt.requestFocus()
            view.et_tt.setBackgroundResource(R.drawable.textbox_error_bg)
        } else if (TextUtils.isEmpty(view.et_bd.text.toString())) {
            view.et_bd.error = "This field is required"
            view.et_bd.requestFocus()
            view.ll_ps.setBackgroundResource(R.drawable.textbox_error_bg)
        } else if (view.ll_da.visibility == View.VISIBLE && view.et_da.text.toString().isEmpty()) {
            view.et_da.error = "This field is required"
            view.et_da.requestFocus()
        } else if (view.mob.visibility == View.VISIBLE && view.et_mobile.text.toString()
                .isEmpty()
        ) {
            view.et_mobile.error = "This field is required"
            view.et_mobile.requestFocus()
        } else if (view.mob.visibility == View.VISIBLE && (!mobilePattern(view.et_mobile.text.toString()))) {
            view.et_mobile.error = "Invalid Recipient Mobile Number"
            view.et_mobile.requestFocus()
        } else if (rg.visibility == View.VISIBLE && rg.checkedRadioButtonId == -1) {
            view.sti_radio_no.error = "This field is required"
            view.sti_radio_no.requestFocus()
        } else if (rg_rd.visibility == View.VISIBLE && rg_rd.checkedRadioButtonId == -1) {
            view.rd_radio_no.error = "This field is required"
            view.rd_radio_no.requestFocus()
        } else if (view.cb_pp.isChecked == false) {
            view.cb_pp.buttonTintList = resources.getColorStateList(R.color.red)
            view.tv_pp.setTextColor(Color.parseColor("#F44336"))
        } else {
            view.pr_fa_ti.isErrorEnabled = false
            view.pr_fa_ti.error = null
            view.ct_fa_ti.isErrorEnabled = false
            view.prf_fa_ti.isErrorEnabled = false
            view.rd_radio_no.error = null
            view.et_tt.error = null
            view.et_bd.error = null
            view.sti_radio_no.error = null
            view.cb_pp.buttonTintList = resources.getColorStateList(R.color.blue)

//            view.te_si.error = null
            return true
        }
        return false
    }



//    private fun checkEditText2(view: View) {
//        if (TextUtils.isEmpty(view.et_pf.text.toString()) ||
//            TextUtils.isEmpty(view.cf_fm.text.toString()) ||
//            TextUtils.isEmpty(view.prf_nm.text.toString()) ||
//            TextUtils.isEmpty(view.et_tt.text.toString()) ||
//            TextUtils.isEmpty(view.et_da.text.toString()) ||
//            TextUtils.isEmpty(view.et_mobile.text.toString()) ||
//            rg_rd.getCheckedRadioButtonId() == -1 ||
//            cb_pp.isChecked == false
//        ) {
//            view.tv_ts_ct_next.setBackgroundResource(R.drawable.rounded_rectangle_blue_disabled)
//            view.tv_ts_ct_next.isEnabled = false
//            view.tv_ts_ct_next.isClickable = false
//        } else {
//            view.tv_ts_ct_next.setBackgroundResource(R.drawable.rounded_rectangle_blue_enabled)
//            view.tv_ts_ct_next.isEnabled = true
//            view.tv_ts_ct_next.isClickable = true
//        }
//    }


//    private fun checker1(view: View) {
//
//
//        view.cf_fm.doAfterTextChanged {
//            checkEditText(view)
//        }
//        view.prf_nm.doAfterTextChanged {
//            checkEditText(view)
//        }
//        view.et_bd.doAfterTextChanged {
//            checkEditText(view)
//        }
//        view.et_tt.doAfterTextChanged {
//            checkEditText(view)
//        }
//        rg.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
//
//            val radio: RadioButton = group.findViewById(checkedId)
////            Log.e("selectedtext-->",radio.text.toString())
//            checkEditText(view)
//
//        })
//
//        cb_pp.setOnCheckedChangeListener { buttonView, isChecked ->
//            checkEditText(view)
//        }
//    }

//    private fun checker2(view: View) {
//        view.et_pf.doAfterTextChanged {
//            checkEditText2(view)
//        }
//        view.cf_fm.doAfterTextChanged {
//            checkEditText2(view)
//        }
//        view.prf_nm.doAfterTextChanged {
//            checkEditText2(view)
//        }
//        view.et_tt.doAfterTextChanged {
//            checkEditText2(view)
//        }
//        view.et_da.doAfterTextChanged {
//            checkEditText2(view)
//        }
//        view.et_mobile.doAfterTextChanged {
//            checkEditText2(view)
//        }
//        cb_pp.setOnCheckedChangeListener { buttonView, isChecked ->
//            checkEditText2(view)
//        }
//    }

}