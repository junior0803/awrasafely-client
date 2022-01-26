package com.pragmanila.awrasafely.ui.fcl

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.pragmanila.awrasafely.R
import com.pragmanila.awrasafely.ui.landingpage.LandingPageActivity
import com.pragmanila.awrasafely.ui.fcl.fragments.ClientTypeFragment
import kotlinx.android.synthetic.main.client_type_fragment.*
import kotlinx.android.synthetic.main.landingpage.*
import kotlinx.android.synthetic.main.fcl_request.*

class FCLRequestActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fcl_request)
        val clientTypeFragment = ClientTypeFragment()

        // Get the support fragment manager instance
        val manager = supportFragmentManager

        // Begin the fragment transition using support fragment manager
        val transaction = manager.beginTransaction()

        // Replace the fragment on container
        transaction.replace(R.id.fragment_container,clientTypeFragment)
        transaction.addToBackStack(null)

        // Finishing the transition
        transaction.commit()

        ll_back.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@FCLRequestActivity, LandingPageActivity::class.java))
            finish()
        })


    }

    public fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.disallowAddToBackStack()
        transaction.commit()
    }
}