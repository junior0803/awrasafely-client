package com.pragmanila.awrasafely.ui.preprequest.fragments

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.pragmanila.awrasafely.R
import com.pragmanila.awrasafely.ui.preprequest.PrepRequestTabsActivity
import kotlinx.android.synthetic.main.client_type_fragment.*
import kotlinx.android.synthetic.main.client_type_fragment.view.*
import kotlinx.android.synthetic.main.client_type_fragment.view.tv_ts_ct_next
import kotlinx.android.synthetic.main.message_box.view.*
import kotlinx.android.synthetic.main.personal_information_fragment.view.*

class PersonalInformationFragment : Fragment(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.prep_personal_information_fragment,container,false)

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

        view.et_uic.doAfterTextChanged {
            checkEditText(view)
        }

        view.et_email.doAfterTextChanged {
            checkEditText(view)
        }

        view.et_mobile.doAfterTextChanged {
            checkEditText(view)
        }

        view.forgot_text.setOnClickListener(View.OnClickListener {
            val messageBoxView = LayoutInflater.from(activity).inflate(R.layout.message_box, null)

            //AlertDialogBuilder
            val messageBoxBuilder = activity?.let { it1 -> AlertDialog.Builder(it1).setView(messageBoxView) }


            //show dialog
            val  messageBoxInstance = messageBoxBuilder?.show()

            //set Listener
            messageBoxView.close.setOnClickListener(){
                //close dialog
                messageBoxInstance?.dismiss()
            }
        })
        view.tv_ts_ct_next.setOnClickListener(View.OnClickListener {
//            (activity as TestingServicesTabsActivity).nextPage()

            (activity as PrepRequestTabsActivity).nextPage()
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragment_container, ExistingRiskAssessmentFragment())
            transaction?.disallowAddToBackStack()
            transaction?.commit()
        })
        return view
    }

    private fun checkEditText(view: View){
        if (TextUtils.isEmpty(view.et_uic.text.toString()) ||
            TextUtils.isEmpty(view.et_email.text.toString()) ||
            TextUtils.isEmpty(view.et_mobile.text.toString())) {
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