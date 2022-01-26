package com.pragmanila.awrasafely.ui.fcl.fragments

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
import com.pragmanila.awrasafely.ui.fcl.FCLRequestTabsActivity
import com.pragmanila.awrasafely.ui.preprequest.PrepRequestTabsActivity
import com.pragmanila.awrasafely.ui.testingservices.TestingServicesActivity
import com.pragmanila.awrasafely.ui.testingservices.TestingServicesTabsActivity
import com.pragmanila.awrasafely.utils.TinyDB
import kotlinx.android.synthetic.main.client_type_fragment.*
import kotlinx.android.synthetic.main.client_type_fragment.view.*

class ClientTypeFragment : Fragment(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    var checkMode = 0;
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.prep_client_type_fragment,container,false)

        view.ll_existing_client.setOnClickListener(View.OnClickListener {
            view.iv_existing_check.visibility = View.VISIBLE
            view.iv_new_check.visibility = View.GONE
            ll_existing_client.setBackgroundResource(R.drawable.rounded_rectangle_clicked)
            ll_new_client.setBackgroundResource(R.drawable.rounded_rectangle)
            tv_ts_ct_next.visibility = View.VISIBLE
            checkMode = 1
        })

        view.ll_new_client.setOnClickListener(View.OnClickListener {
            view.iv_existing_check.visibility = View.GONE
            view.iv_new_check.visibility = View.VISIBLE
            ll_new_client.setBackgroundResource(R.drawable.rounded_rectangle_clicked)
            ll_existing_client.setBackgroundResource(R.drawable.rounded_rectangle)
            tv_ts_ct_next.visibility = View.VISIBLE
            checkMode = 2
        })

        view.tv_ts_ct_next.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, FCLRequestTabsActivity::class.java)
            intent.putExtra("mode", checkMode.toString())
            startActivity(intent)
//                activity?.startActivity(Intent(activity, TestingServicesTabsActivity::class.java))
//                activity?.finish()
//            val transaction = activity?.supportFragmentManager?.beginTransaction()
//            transaction?.replace(R.id.fragment_container, PersonalInformationFragment())
//            transaction?.disallowAddToBackStack()
//            transaction?.commit()
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