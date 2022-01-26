package com.pragmanila.awrasafely.ui.testingservices.fragments

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.pragmanila.awrasafely.R
import com.pragmanila.awrasafely.ui.landingpage.LandingPageActivity
import com.pragmanila.awrasafely.ui.preprequest.PrepRequestActivity
import kotlinx.android.synthetic.main.client_type_fragment.*
import kotlinx.android.synthetic.main.client_type_fragment.view.*
import kotlinx.android.synthetic.main.summary_fragment.view.*
import kotlinx.android.synthetic.main.testing_services_tabs.*
import kotlinx.android.synthetic.main.testing_services_tabs.view.*

class SummaryFragment : Fragment(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.summary_fragment,container,false)

//        view.ll_existing_client.setOnClickListener(View.OnClickListener {
//            view.iv_existing_check.visibility = View.VISIBLE
//            view.iv_new_check.visibility = View.GONE
//            ll_existing_client.setBackgroundResource(R.drawable.rounded_rectangle_clicked)
//            ll_new_client.setBackgroundResource(R.drawable.rounded_rectangle)
//            tv_ts_ct_next.visibility = View.VISIBLE
//        })
//
        val mobileNumber = view.mobile_number
        val args = this.arguments
        val inputMobile =args?.get("mobile")
        mobileNumber.text = inputMobile.toString()

        view.tv_ts_ct_done.setOnClickListener(View.OnClickListener {
            activity?.startActivity(Intent(activity, LandingPageActivity::class.java))
            activity?.finish()
        })

        return view
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