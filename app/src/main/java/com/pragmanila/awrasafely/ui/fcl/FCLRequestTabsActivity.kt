package com.pragmanila.awrasafely.ui.fcl

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.pragmanila.awrasafely.R
import com.pragmanila.awrasafely.ui.fcl.fragments.NewPersonalInformationFragment
import com.pragmanila.awrasafely.ui.fcl.fragments.PersonalInformationFragment
import kotlinx.android.synthetic.main.landingpage.*
import kotlinx.android.synthetic.main.prep_request.*
import kotlinx.android.synthetic.main.prep_request.ll_back
import kotlinx.android.synthetic.main.prep_request_tabs.*

class FCLRequestTabsActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fcl_request_tabs)
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
            startActivity(Intent(this@FCLRequestTabsActivity, FCLRequestActivity::class.java))
            finish()

//            stepper.goToNextStep()
        })

    }

    public fun nextPage(){
//        stepper.goToNextStep()
    }


}