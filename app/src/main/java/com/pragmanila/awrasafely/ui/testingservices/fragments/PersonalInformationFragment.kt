package com.pragmanila.awrasafely.ui.testingservices.fragments

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.pragmanila.awrasafely.R
import com.pragmanila.awrasafely.ui.testingservices.TestingServicesTabsActivity
import kotlinx.android.synthetic.main.client_type_fragment.*
import kotlinx.android.synthetic.main.client_type_fragment.view.*
import kotlinx.android.synthetic.main.client_type_fragment.view.tv_ts_ct_next
import kotlinx.android.synthetic.main.message_box.view.*
import kotlinx.android.synthetic.main.new_personal_information_fragment.*
import kotlinx.android.synthetic.main.new_personal_information_fragment.rg
import kotlinx.android.synthetic.main.new_personal_information_fragment.view.*
import kotlinx.android.synthetic.main.personal_information_fragment.*
import kotlinx.android.synthetic.main.personal_information_fragment.view.*
import kotlinx.android.synthetic.main.personal_information_fragment.view.et_mobile
import kotlinx.android.synthetic.main.risk_assessment_fragment.*
import kotlinx.android.synthetic.main.risk_assessment_fragment.view.*
import kotlinx.android.synthetic.main.summary_fragment.*
import java.util.*
import java.util.regex.Pattern

class PersonalInformationFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
        val view = inflater.inflate(R.layout.personal_information_fragment, container, false)


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


        //passing data to summary
        val personalFragment = PersonalInformationFragment()




        view.et_uic.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                view.et_uic.setBackgroundResource(R.drawable.textbox_unclicked)
                val input1 = view.et_uic.text.toString().uppercase(Locale.getDefault())
                view.et_uic.text = Editable.Factory.getInstance().newEditable(input1)
                val uic = view.et_uic.text.toString().length
                if (uic < 14) {
                    view.uic_ti.error = "Invalid UIC format"
                    view.et_uic.setBackgroundResource(R.drawable.textbox_error_bg)
                } else {
                    view.uic_ti.error = null
                    view.uic_ti.isErrorEnabled = false
                    view.et_uic.setBackgroundResource(R.drawable.textbox_unclicked)
                }
            } else if (hasFocus) {
                view.et_uic.setBackgroundResource(R.drawable.textbox_clicked)
            }
        }

        view.et_uic.setOnClickListener {
            view.et_uic.setBackgroundResource(R.drawable.textbox_clicked)
            et_uic.requestFocus()
        }

        view.et_email.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(et_email.text.toString())
                        .matches() && view.et_email.getText().toString().trim().length >= 1
                ) {
                    view.em_ti.error = "Invalid Email Address"
                    view.et_email.setBackgroundResource(R.drawable.textbox_error_bg)

                } else if (view.et_email.text.toString().isEmpty()
                ) {
                    view.em_ti.error = "This field is required"
                    view.et_email.setBackgroundResource(R.drawable.textbox_error_bg)

                } else {
                    view.em_ti.error = null
                    view.em_ti.isErrorEnabled = false
                    view.et_email.setBackgroundResource(R.drawable.textbox_unclicked)
                }
            } else if (hasFocus) {
                view.et_email.setBackgroundResource(R.drawable.textbox_clicked)
            }
        }
        view.et_mobile.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                if (TextUtils.isEmpty(view.et_mobile.text.toString())) {
                    view.et_mobile.error = "This field is required"
                    view.mob_err.setText("This field is required")
                    view.mob_err.visibility = View.VISIBLE
                    view.ec_mobile_extension.setBackgroundResource(R.drawable.textbox_error_bg)
                } else if (!mobilePattern(view.et_mobile.text.toString())) {
                    view.et_mobile.error = "Invalid Mobile number"
                    view.mob_err.setText("Invalid Mobile Number")
                    view.mob_err.visibility = View.VISIBLE
                    view.ec_mobile_extension.setBackgroundResource(R.drawable.textbox_error_bg)
                } else {
                    view.mob_err.visibility = View.GONE
                    view.mob_err.setText("This field is required")
                    view.et_mobile.error = null
                    view.ec_mobile_extension.setBackgroundResource(R.drawable.textbox_unclicked)
                }

            } else if (hasFocus) {
                view.ec_mobile_extension.setBackgroundResource(R.drawable.textbox_clicked)
            }
        }






        view.forgot_text.setOnClickListener(View.OnClickListener {
            val messageBoxView = LayoutInflater.from(activity).inflate(R.layout.message_box, null)

            //AlertDialogBuilder
            val messageBoxBuilder =
                activity?.let { it1 -> AlertDialog.Builder(it1).setView(messageBoxView) }


            //show dialog
            val messageBoxInstance = messageBoxBuilder?.show()

            //set Listener
            messageBoxView.close.setOnClickListener() {
                //close dialog
                messageBoxInstance?.dismiss()
            }
        })
        view.tv_ts_ct_next.setOnClickListener(View.OnClickListener {
            checkEditText(view)
            if (checkEditText(view)) {
                (activity as TestingServicesTabsActivity).nextPage()
//                val transaction = activity?.supportFragmentManager?.beginTransaction()
//                transaction?.replace(R.id.fragment_container, RiskAssessmentFragment())
//                transaction?.addToBackStack(null)
//                transaction?.commit()
            } else {
                checkEditText(view)
            }
        })
        return view
    }

    private fun checkEditText(view: View): Boolean {
        if (TextUtils.isEmpty(view.et_uic.text.toString()) || view.et_uic.text.toString().length < 14) {
            view.uic_ti.error = "This field is required"
            view.et_uic.setBackgroundResource(R.drawable.textbox_error_bg)
            view.et_uic.requestFocus()
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(et_email.text.toString())
                .matches()
        ) {
            view.em_ti.error = "Invalid Email Address"
            view.em_ti.isErrorEnabled = true
            view.et_email.requestFocus()
        } else if (TextUtils.isEmpty(view.et_email.text.toString())
        ) {
            view.em_ti.error = "This field is required"
            view.em_ti.isErrorEnabled = true
            view.et_email.requestFocus()
        } else if (TextUtils.isEmpty(view.et_mobile.text.toString())) {
            view.et_mobile.error = "This field is required"
            view.mob_err.setText("This field is required")
            view.mob_err.visibility = View.VISIBLE
            view.ec_mobile_extension.setBackgroundResource(R.drawable.textbox_error_bg)
            view.et_mobile.requestFocus()
        } else if (!mobilePattern(view.et_mobile.text.toString()) && !TextUtils.isEmpty(view.et_mobile.text.toString())) {
            view.et_mobile.error = "Invalid Mobile Number"
            view.mob_err.setText("Invalid Mobile Number")
            view.ec_mobile_extension.setBackgroundResource(R.drawable.textbox_error_bg)
            view.et_mobile.requestFocus()
        } else {
            view.et_uic.setBackgroundResource(R.drawable.textbox_unclicked)
            view.mob_err.visibility = View.GONE
            view.uic_ti.error = null
            view.em_ti.error = null
            view.et_mobile.error = null
            return true
        }
        return false

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