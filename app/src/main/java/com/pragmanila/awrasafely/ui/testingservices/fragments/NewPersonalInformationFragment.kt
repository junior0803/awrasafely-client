package com.pragmanila.awrasafely.ui.testingservices.fragments

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.pragmanila.awrasafely.MainViewModel
//import com.pragmanila.awrasafely.MainViewModelFactory
import com.pragmanila.awrasafely.R
import com.pragmanila.awrasafely.api.model.User
import com.pragmanila.awrasafely.api.model.UserResponse
import com.pragmanila.awrasafely.ui.testingservices.ApiValidationFragment
//import com.pragmanila.awrasafely.repository.Repository
import com.pragmanila.awrasafely.ui.testingservices.TestingServicesTabsActivity
import kotlinx.android.synthetic.main.error_dialog.*
import kotlinx.android.synthetic.main.error_dialog.view.*
import kotlinx.android.synthetic.main.fcl_request_tabs.*
import kotlinx.android.synthetic.main.new_personal_information_fragment.*
import kotlinx.android.synthetic.main.new_personal_information_fragment.rg
import kotlinx.android.synthetic.main.new_personal_information_fragment.view.*
import kotlinx.android.synthetic.main.risk_assessment_fragment.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

class NewPersonalInformationFragment : Fragment() {
    var nbday: String? = null
    var res = false
    var mn: String? = null
    var fn: String? = null
    var bo: Int? = null

    //private lateinit var viewModel: MainViewModel
    private val viewModel:MainViewModel by activityViewModels()


    private fun mobilePattern(text: String?): Boolean {
        var p = Pattern.compile("[9][0-9]{9}")
        var m = p.matcher(text)
        return m.matches()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.new_personal_information_fragment, container, false)
        val calendar = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Calendar.getInstance()
        } else {
            TODO("VERSION.SDK_INT < N")
        }
        //Next Button
        view.tv_ts_ct_next.setOnClickListener {
            checkEditText(view)
            if (checkEditText(view)){
                initViewModel()
                createUser()
            } else {
                checkEditText(view)
            }
        }

        // Junior patch begin
        val navController = findNavController()

        if (requireActivity().intent.getStringExtra("mode").toString().equals("1")) {
            navController.navigate(R.id.step_exist_person_info)
        }

        // Junior patch end
//        view.ef_si.setOnClickListener {
//            val view: View = layoutInflater.inflate(R.layout.si_bottom_sheet,null  )
//            val dialog = activity?.let { it1 -> BottomSheetDialog(it1) }
//            dialog?.setContentView(view)
//            dialog?.show()
//        }

        //email validation


        view.et_bd.setOnClickListener {
            view.ll_birthdate.setBackgroundResource(R.drawable.textbox_clicked)
            view.et_bd.error = null
            view.et_err.visibility = View.GONE
            val dpd = DatePickerDialog(
                activity as TestingServicesTabsActivity,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, monthOfYear)
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    // Display Selected date in textbox
                    val sdf = SimpleDateFormat("yyyy-MM-dd")
                    nbday = sdf.format(calendar.getTime())
                    val bdy = SimpleDateFormat("MM/DD/YYYY")
                    val bday = bdy.format(calendar.getTime())
                    et_bd.text = Editable.Factory.getInstance().newEditable(bday)


                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)

            )

            dpd.setOnCancelListener {
                if (TextUtils.isEmpty(view.et_bd.text.toString())) {
                    view.ll_birthdate.setBackgroundResource(R.drawable.textbox_error_bg)
                    view.et_bd.error = "This field is required"
                    view.et_err.visibility = View.VISIBLE
                } else {
                    view.ll_birthdate.setBackgroundResource(R.drawable.textbox_unclicked)
                    view.et_bd.error = null
                    view.et_err.visibility = View.GONE
                }

            }
            dpd.datePicker.maxDate =
                System.currentTimeMillis() - 568025136000L
            dpd.show()

        }
        view.et_bd.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                if (!TextUtils.isEmpty(view.et_bd.text.toString())) {
                    view.ll_birthdate.setBackgroundResource(R.drawable.textbox_unclicked)
                    view.et_bd.error = null
                    view.et_err.visibility = View.GONE
                } else if (TextUtils.isEmpty(view.et_bd.text.toString())) {
                    view.ll_birthdate.setBackgroundResource(R.drawable.textbox_error_bg)
                    view.et_bd.error = "This field is required"
                    view.et_err.visibility = View.VISIBLE
                } else {
                    view.ll_birthdate.setBackgroundResource(R.drawable.textbox_unclicked)
                    view.et_bd.error = null
                    view.et_err.visibility = View.GONE
                }


            } else if (hasFocus) {
                view.ll_birthdate.setBackgroundResource(R.drawable.textbox_clicked)
                view.et_bd.error = null
                view.et_err.visibility = View.GONE
                val dpd = DatePickerDialog(
                    activity as TestingServicesTabsActivity,
                    DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                        calendar.set(Calendar.YEAR, year)
                        calendar.set(Calendar.MONTH, monthOfYear)
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                        // Display Selected date in textbox
                        val sdf = SimpleDateFormat("yyyy-MM-dd")
                        nbday = sdf.format(calendar.getTime())
                        val bdy = SimpleDateFormat("MM/DD/YYYY")
                        val bday = bdy.format(calendar.getTime())
                        et_bd.text = Editable.Factory.getInstance().newEditable(bday)

                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)

                )

                dpd.setOnCancelListener {
                    if (TextUtils.isEmpty(view.et_bd.text.toString())) {
                        view.ll_birthdate.setBackgroundResource(R.drawable.textbox_error_bg)
                        view.et_bd.error = "This field is required"
                        view.et_err.visibility = View.VISIBLE
                    } else {
                        view.ll_birthdate.setBackgroundResource(R.drawable.textbox_unclicked)
                        view.et_bd.error = null
                        view.et_err.visibility = View.GONE
                    }

                }
                dpd.datePicker.maxDate =
                    System.currentTimeMillis() - 568025136000L
                dpd.show()
            }
        }



        view.et_mn.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                view.et_mn.setBackgroundResource(R.drawable.textbox_unclicked)

                val input1 = view.et_mn.text.toString().uppercase(Locale.getDefault())
                view.et_mn.text = Editable.Factory.getInstance().newEditable(input1)
                when {
                    view.et_mn.text.toString().trim().isEmpty() -> {
                        view.et_mn.setBackgroundResource(R.drawable.textbox_error_bg)
                        view.te_mn.error = "This field is required"
                        view.te_mn.isErrorEnabled = true

                    }
                    view.et_mn.text.toString().trim().length == 1 -> {
                        view.et_mn.setBackgroundResource(R.drawable.textbox_error_bg)
                        view.te_mn.error = "Invalid first 2 letters of Mother's Name"
                        view.te_mn.isErrorEnabled = true

                    }
                    else -> {
                        // your code here
                        view.te_mn.isErrorEnabled = false
                        view.te_mn.error = null

                    }
                }
            } else if (hasFocus) {
                if (view.te_mn.error == null) {
                    view.et_mn.setBackgroundResource(R.drawable.textbox_clicked)

                } else {
                    view.et_mn.setBackgroundResource(R.drawable.textbox_error_bg)
                }

            }
        }
//        view.et_mn.doAfterTextChanged{
//            if (view.et_mn.getText().toString().trim().length < 1) {
//            view.tv_ts_ct_next.setBackgroundResource(R.drawable.rounded_rectangle_blue_disabled)
//            view.tv_ts_ct_next.isEnabled = false
//            view.tv_ts_ct_next.isClickable = false
//            view.te_mn.error="This field is required"
//
//        }else if (view.et_mn.getText().toString().trim().length== 1){
//            view.te_mn.error="Invalid first 2 letters of Mother's Name"
//            view.tv_ts_ct_next.setBackgroundResource(R.drawable.rounded_rectangle_blue_disabled)
//            view.tv_ts_ct_next.isEnabled = false
//            view.tv_ts_ct_next.isClickable = false
//
//        } else {
//            // your code here
//            view.te_mn.error=null
//            checkEditText(view)
//
//        } }

        view.et_fn.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                view.et_fn.setBackgroundResource(R.drawable.textbox_unclicked)

                val input1 = view.et_fn.text.toString().uppercase(Locale.getDefault())
                view.et_fn.text = Editable.Factory.getInstance().newEditable(input1)
                when {
                    view.et_fn.text.toString().trim().isEmpty() -> {
                        view.et_fn.setBackgroundResource(R.drawable.textbox_error_bg)
                        view.te_fn.error = "This field is required"
                        view.te_fn.isErrorEnabled = true
                    }
                    view.et_fn.text.toString().trim().length == 1 -> {
                        view.et_fn.setBackgroundResource(R.drawable.textbox_error_bg)
                        view.te_fn.error = "Invalid first 2 letters of Father's Name"
                        view.te_fn.isErrorEnabled = true
                    }
                    else -> {
                        // your code here
                        view.te_fn.error = null
                        view.te_fn.isErrorEnabled = false
                    }
                }
            } else if (hasFocus) {
                if (view.te_fn.error == null) {
                    view.et_fn.setBackgroundResource(R.drawable.textbox_clicked)

                } else {
                    view.et_fn.setBackgroundResource(R.drawable.textbox_error_bg)
                }

            }
        }

        view.ts_em.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                view.ts_em.setBackgroundResource(R.drawable.textbox_unclicked)
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(ts_em.text.toString())
                        .matches() && view.ts_em.text.toString().trim().isNotEmpty()
                ) {
                    view.ts_em.setBackgroundResource(R.drawable.textbox_error_bg)
                    view.te_em.error = "Invalid Email Address"
                    view.te_em.isErrorEnabled = true

                } else if (view.ts_em.text.toString().trim().isEmpty()
                ) {
                    view.ts_em.setBackgroundResource(R.drawable.textbox_error_bg)
                    view.te_em.error = "This field is required"
                    view.te_em.isErrorEnabled = true

                } else {
                    view.ts_em.setBackgroundResource(R.drawable.textbox_unclicked)
                    view.te_em.isErrorEnabled = false
                    view.te_em.error = null
                }
            } else if (hasFocus) {
                view.ts_em.setBackgroundResource(R.drawable.textbox_clicked)
            }
        }


        view.ot_si.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                if (view.ot_si.text.toString().trim().isEmpty()) {
                    view.te_ot.error = "This field is required"
                    view.te_ot.isErrorEnabled = true
                    view.ot_si.setBackgroundResource(R.drawable.textbox_error_bg)
                } else {
                    // your code here
                    view.te_ot.error = null
                    view.ot_si.setBackgroundResource(R.drawable.textbox_unclicked)
                    view.te_ot.isErrorEnabled = false
                }
            }
        }



        view.et_bo.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                view.et_bo.setBackgroundResource(R.drawable.textbox_unclicked)

                if (view.et_bo.text.toString().trim().isEmpty()) {
                    view.et_bo.setBackgroundResource(R.drawable.textbox_error_bg)
                    view.te_bo.error = "This field is required"
                    view.te_bo.isErrorEnabled = true
                } else if (view.et_bo.text.toString() == "00" || view.et_bo.text
                        .toString() == "0"
                ) {
                    view.et_bo.setBackgroundResource(R.drawable.textbox_error_bg)
                    view.te_bo.error = "Invalid Birth order"
                    view.te_bo.isErrorEnabled = true
                } else {
                    // your code here
                    view.te_bo.error = null
                    view.te_bo.isErrorEnabled = false
                }
            } else if (hasFocus) {
                if (view.te_bo.error == null) {
                    view.et_bo.setBackgroundResource(R.drawable.textbox_clicked)

                } else {
                    view.et_bo.setBackgroundResource(R.drawable.textbox_error_bg)
                }

            }
        }



        view.et_mobile.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                when {
                    !mobilePattern(view.et_mobile.text.toString()) || view.et_mobile.length() < 10 -> {
                        // Not allowed
                        view.et_mobile.error = "Invalid Mobile Number"
                        view.nmob_err.setText("Invalid Mobile Number")
                        view.nmob_err.visibility = View.VISIBLE
                        view.ll_mobile_extension.setBackgroundResource(R.drawable.textbox_error_bg)

                    }
                    TextUtils.isEmpty(view.et_mobile.text.toString()) -> {
                        view.et_mobile.error = "This field is required"
                        view.nmob_err.setText("This field is required")
                        view.nmob_err.visibility = View.VISIBLE
                        view.ll_mobile_extension.setBackgroundResource(R.drawable.textbox_error_bg)

                    }
                    else -> {
                        view.et_mobile.error = null
                        view.nmob_err.setText("This field is required")
                        view.nmob_err.visibility = View.GONE
                        view.ll_mobile_extension.setBackgroundResource(R.drawable.textbox_unclicked)

                    }
                }
            } else if (hasFocus) {
                view.ll_mobile_extension.setBackgroundResource(R.drawable.textbox_clicked)

            }
        }


        //radioGroup
        view.radio_male.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                view.radio_male.setBackgroundResource(R.drawable.textbox_unclicked)
            } else if (hasFocus) {
                view.radio_male.isChecked = true
                view.radio_male.setBackgroundResource(R.drawable.textbox_clicked)
                view.radio_female.isChecked = false
            }
        }

        view.radio_female.setOnClickListener() {
            radio_male.isChecked = false
            view.radio_male.setBackgroundResource(R.drawable.textbox_unclicked)
            view.radio_female.setBackgroundResource(R.drawable.textbox_clicked)
            view.radio_female.isChecked = true
            view.radio_female.setError(null)
        }
        view.radio_female.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                view.radio_female.setBackgroundResource(R.drawable.textbox_unclicked)
                view.radio_female.error = null
            } else if (hasFocus) {
                if (rg.checkedRadioButtonId == -1 && radio_female.error != null) {
                    view.radio_male.isChecked = false
                    view.radio_female.setBackgroundResource(R.drawable.textbox_error_bg)
                    view.radio_male.setBackgroundResource(R.drawable.textbox_error_bg)
                    view.radio_female.isChecked = false
                } else if (rg.checkedRadioButtonId == 0) {
                    radio_male.isChecked = false
                    view.radio_male.setBackgroundResource(R.drawable.textbox_unclicked)
                    view.radio_female.setBackgroundResource(R.drawable.textbox_clicked)
                    view.radio_female.isChecked = true
                } else {
                    radio_male.isChecked = false
                    view.radio_male.setBackgroundResource(R.drawable.textbox_unclicked)
                    view.radio_female.setBackgroundResource(R.drawable.textbox_clicked)
                    view.radio_female.isChecked = true
                }
            }
        }


        view.ef_si.setOnClickListener {
            view.ef_si.setBackgroundResource(R.drawable.textbox_clicked)
            val builder: android.app.AlertDialog.Builder =
                android.app.AlertDialog.Builder(activity, R.style.MyAlertDialogTheme)
            builder.setTitle("Self Identity")

            val checkedItem: Int = when {
                ef_si.text.toString() == "Male" -> {
                    0
                }
                ef_si.text.toString() == "Female" -> {
                    1
                }
                ef_si.text.toString() == "Others" -> {
                    2
                }
                else -> {
                    -1
                }
            }
            //this will checked the item when user open the dialog

            val typeOfSex = arrayOf("Male", "Female", "Others")
            builder.setSingleChoiceItems(
                typeOfSex, checkedItem
            ) { _, which ->
                ef_si.text = Editable.Factory.getInstance().newEditable(typeOfSex[which])
                if (view.ef_si.text.toString().trim() == "Others") {
                    ot_text.visibility = View.VISIBLE
                    te_ot.visibility = View.VISIBLE
                    te_ot.error = null
                } else {
                    ot_text.visibility = View.GONE
                    te_ot.visibility = View.GONE
                }
//                    Toast.makeText(
//                        activity,
//                        "Position: " + which + " Value: " + listItems[which],
//                        Toast.LENGTH_LONG
//                    ).show()
            }

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

            builder.setPositiveButton(
                "Confirm"
            ) { dialog, _ ->
                dialog.dismiss()
                view.si_error.visibility = View.VISIBLE
                view.si_error.visibility = View.GONE
                view.ef_si.setBackgroundResource(R.drawable.textbox_clicked)

            }

            val dialog: android.app.AlertDialog? = builder.create()
            dialog?.show()
        }
        view.ef_si.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val input1 = view.ef_si.text.toString()
                view.ef_si.text = input1
                if (view.ef_si.text.toString().isEmpty()) {
                    view.si_error.visibility = View.VISIBLE
                    view.ef_si.setBackgroundResource(R.drawable.textbox_error_bg)
                } else {
                    view.si_error.visibility = View.GONE
                    view.ef_si.setBackgroundResource(R.drawable.textbox_unclicked)

                }

            } else if (hasFocus) {
                view.ef_si.setBackgroundResource(R.drawable.textbox_clicked)

                val builder: android.app.AlertDialog.Builder =
                    android.app.AlertDialog.Builder(activity, R.style.MyAlertDialogTheme)
                builder.setTitle("Self Identity")


                val checkedItem: Int = when {
                    ef_si.text.toString() == "Male" -> {
                        0
                    }
                    ef_si.text.toString() == "Female" -> {
                        1
                    }
                    ef_si.text.toString() == "Others" -> {
                        2
                    }
                    else -> {
                        -1
                    }
                }
                //this will checked the item when user open the dialog

                val typeOfSex = arrayOf("Male", "Female", "Others")
                builder.setSingleChoiceItems(
                    typeOfSex, checkedItem
                ) { _, which ->
                    ef_si.text = Editable.Factory.getInstance().newEditable(typeOfSex[which])
                    if (view.ef_si.text.toString().trim() == "Others") {
                        ot_text.visibility = View.VISIBLE
                        te_ot.visibility = View.VISIBLE
                        te_ot.error = null
                    } else {
                        ot_text.visibility = View.GONE
                        te_ot.visibility = View.GONE
                    }
//                    Toast.makeText(
//                        activity,
//                        "Position: " + which + " Value: " + listItems[which],
//                        Toast.LENGTH_LONG
//                    ).show()
                }

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

                builder.setPositiveButton(
                    "Confirm"
                ) { dialog, _ ->
                    dialog.dismiss()
                    view.si_error.visibility = View.GONE
                    view.ef_si.setBackgroundResource(R.drawable.textbox_clicked)

                }

                val dialog: android.app.AlertDialog? = builder.create()
                dialog?.show()
            }
        }

        return view
    }
    private fun createUser() {
        mn = et_mn.text.toString()
        fn = et_fn.text.toString()
        bo = et_bo.text.toString().toInt()
        val myPost = User(
            mn!!,
            fn!!,
            bo!!,
            nbday!!
        )

        viewModel.setUserInfo(myPost)

        //viewModel.createNewUser(myPost)

    }
    private fun initViewModel() {
        val mDialogView = LayoutInflater.from(activity)
            .inflate(R.layout.error_dialog, null);
        val builder:AlertDialog.Builder=AlertDialog.Builder(activity)
        val mbuilder = AlertDialog.Builder(activity)
            .setView(mDialogView)
        val mAlertDialog = mbuilder.create()
        loadFragment()
    }

    private fun loadFragment() {
        (activity as TestingServicesTabsActivity).nextPage()
    }

    private fun checkEditText(view: View):Boolean {
        if (TextUtils.isEmpty(view.et_mn.text.toString())) {
            view.te_mn.error = "This field is required"
            view.te_mn.requestFocus()
            view.et_mn.setBackgroundResource(R.drawable.textbox_error_bg)
        } else if (view.et_mn.text.toString().trim().length < 2) {
            view.te_mn.error = "Invalid first 2 letters of Mother's Name"
            view.te_mn.requestFocus()
            view.et_mn.setBackgroundResource(R.drawable.textbox_error_bg)
        } else if (TextUtils.isEmpty(view.et_fn.text.toString())) {
            view.te_fn.error = "This field is required"
            view.te_fn.requestFocus()
            view.et_fn.setBackgroundResource(R.drawable.textbox_error_bg)
        } else if (view.et_fn.text.toString().trim().length < 2) {
            view.te_fn.error = "Invalid first 2 letters of Father's Name"
            view.te_fn.requestFocus()
            view.et_fn.setBackgroundResource(R.drawable.textbox_error_bg)
        } else if (TextUtils.isEmpty(view.et_bo.text.toString())) {
            view.te_bo.error = "This field is required"
            view.te_bo.requestFocus()
            view.et_bo.setBackgroundResource(R.drawable.textbox_error_bg)
        } else if (view.et_bo.text.toString() == "00") {
            view.te_bo.error = "Invalid Birth order"
            view.te_bo.isErrorEnabled = true
        } else if (TextUtils.isEmpty(view.et_bd.text.toString())) {
            view.et_bd.error = "This field is required"
            view.et_err.visibility = View.VISIBLE
            view.et_bd.requestFocus()
            view.ll_birthdate.setBackgroundResource(R.drawable.textbox_error_bg)
        } else if (rg.checkedRadioButtonId == -1) {
            view.radio_female.error = "This field is required"
            view.radio_female.requestFocus()
        } else if (TextUtils.isEmpty(view.ef_si.text.toString())) {
            view.si_error.visibility = View.VISIBLE
            view.ef_si.setBackgroundResource(R.drawable.textbox_error_bg)
            view.ef_si.requestFocus()
        } else if (TextUtils.isEmpty(view.ts_em.text.toString())) {
            view.te_em.error = "This field is required"
            view.te_em.requestFocus()
            view.ts_em.setBackgroundResource(R.drawable.textbox_error_bg)
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(ts_em.text.toString())
                .matches()
        ) {
            view.ts_em.setBackgroundResource(R.drawable.textbox_error_bg)
            view.te_em.error = "Invalid Email Address"
            view.te_em.isErrorEnabled = true
            view.te_em.requestFocus()
        } else if (TextUtils.isEmpty(view.et_mobile.text.toString())) {
            view.et_mobile.error = "This field is required"
            view.et_mobile.requestFocus()
            view.nmob_err.setText("This field is required")
            view.nmob_err.visibility = View.VISIBLE
            view.ll_mobile_extension.setBackgroundResource(R.drawable.textbox_error_bg)
        } else if (view.et_mobile.length() < 10) {
            view.et_mobile.error = "Invalid Mobile Number"
            view.et_mobile.requestFocus()
            view.nmob_err.setText("Invalid Mobile Number")
            view.nmob_err.visibility = View.VISIBLE
            view.ll_mobile_extension.setBackgroundResource(R.drawable.textbox_error_bg)
        } else if (!mobilePattern(view.et_mobile.text.toString())) {
            view.et_mobile.error = "Invalid Mobile Number"
            view.et_mobile.requestFocus()
            view.nmob_err.setText("Invalid Mobile Number")
            view.nmob_err.visibility = View.VISIBLE
            view.ll_mobile_extension.setBackgroundResource(R.drawable.textbox_error_bg)
        } else if (view.te_ot.visibility == View.VISIBLE && view.ot_si.text.toString().isEmpty()) {
            view.te_ot.error = "This field is required"
            view.ot_si.requestFocus()
            view.ot_si.setBackgroundResource(R.drawable.textbox_error_bg)
        } else {
            mn = et_mn.text.toString()
            fn = et_fn.text.toString()
            bo = et_bo.text.toString().toInt()
            Log.d("log", nbday!!)
            return true
            }

//            view.te_si.error = null
        return false
    }

}

