package com.pragmanila.awrasafely.ui.arv

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.pragmanila.awrasafely.R
import com.pragmanila.awrasafely.ui.arv.fragments.NewPersonalInformationFragment
import com.pragmanila.awrasafely.ui.arv.fragments.PersonalInformationFragment
import kotlinx.android.synthetic.main.landingpage.*
import kotlinx.android.synthetic.main.arv_request.*
import kotlinx.android.synthetic.main.arv_request.ll_back
import kotlinx.android.synthetic.main.arv_request_tabs.*
import kotlinx.android.synthetic.main.arv_request_tabs.stepper

class ARVRequestTabsActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.arv_request_tabs)
//        var mode = intent.getStringExtra("mode")
        Log.e("TEST>>>", intent.getStringExtra("mode").toString())
//        Log.e
        if(intent.getStringExtra("mode").toString().equals("1")){
            val personalInformationFragment = PersonalInformationFragment()
            val manager = supportFragmentManager
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.fragment_container,personalInformationFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        } else {
            val personalInformationFragment = NewPersonalInformationFragment()
            val manager = supportFragmentManager
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.fragment_container,personalInformationFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        ll_back.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@ARVRequestTabsActivity, ARVRequestActivity::class.java))
            finish()
        })

    }

    public fun nextPage(){
        //stepper.setImageResource(int)
        stepper.goToNextStep()
    }


}