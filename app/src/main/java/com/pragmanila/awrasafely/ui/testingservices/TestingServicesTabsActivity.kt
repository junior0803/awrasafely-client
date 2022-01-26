package com.pragmanila.awrasafely.ui.testingservices

import android.app.FragmentManager
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.aceinteract.android.stepper.StepperNavListener
import com.aceinteract.android.stepper.StepperNavigationView
import com.pragmanila.awrasafely.R
import com.pragmanila.awrasafely.ui.landingpage.LandingPageActivity
import com.pragmanila.awrasafely.ui.testingservices.fragments.ClientTypeFragment
import com.pragmanila.awrasafely.ui.testingservices.fragments.NewPersonalInformationFragment
import com.pragmanila.awrasafely.ui.testingservices.fragments.PersonalInformationFragment
import kotlinx.android.synthetic.main.landingpage.*
import kotlinx.android.synthetic.main.landingpage_layout.view.*
import kotlinx.android.synthetic.main.summary_fragment.*
import kotlinx.android.synthetic.main.testing_services.*
import kotlinx.android.synthetic.main.testing_services.ll_back
import kotlinx.android.synthetic.main.testing_services_tabs.*
import kotlinx.android.synthetic.main.testing_services_tabs.toolbar
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.navigation.NavigationView
import ng.softcom.android.utils.ui.findNavControllerFromFragmentContainer
import ng.softcom.android.utils.ui.showToast
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.pragmanila.awrasafely.MainViewModel
import com.pragmanila.awrasafely.api.model.User
import com.pragmanila.awrasafely.ui.testingservices.fragments.RiskAssessmentFragment

class TestingServicesTabsActivity : AppCompatActivity(), StepperNavListener {

    var step = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.testing_services_tabs)
//        var mode = intent.getStringExtra("mode")
        Log.e("TEST>>>", intent.getStringExtra("mode").toString())
//        Log.e

//        if (intent.getStringExtra("mode").toString().equals("1")) {
//            val personalInformationFragment = PersonalInformationFragment()
//            val manager = supportFragmentManager
//            val transaction = manager.beginTransaction()
//            transaction.replace(R.id.fragment_container, personalInformationFragment)
//            transaction.disallowAddToBackStack()
//            transaction.commit()
//        }
//        else {
//            val personalInformationFragment = NewPersonalInformationFragment()
//            val manager = supportFragmentManager
//            val transaction = manager.beginTransaction()
//            transaction.replace(R.id.fragment_container, personalInformationFragment)
//            transaction.disallowAddToBackStack()
//            transaction.commit()
//        }

//        val myFragment =
//            supportFragmentManager.findFragmentByTag("MY_FRAGMENT")
//        if (myFragment != null && myFragment.isVisible()) {
//            // add your code here
//            ll_back.visibility=View.GONE
//        }
        stepper.initializeStepper()
        setSupportActionBar(toolbar)

        setupActionBarWithNavController(findNavControllerFromFragmentContainer(R.id.fragment_container))

        ll_back.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })
    }

    private fun StepperNavigationView.initializeStepper() {

        stepperNavListener = this@TestingServicesTabsActivity
        setupWithNavController(findNavControllerFromFragmentContainer(R.id.fragment_container))
    }

    override fun onBackPressed() {
        if (stepper.currentStep == 0) {
            super.onBackPressed()
        } else if (stepper.currentStep == 1) {
            if (step == 0){
                Toast.makeText(this, "Please check again to confirm.", Toast.LENGTH_SHORT).show()
                step ++
            } else {
                step = 0;
                super.onBackPressed()
            }
        } else if (stepper.currentStep == 3) {
            Toast.makeText(this, "Please confirm User Information", Toast.LENGTH_SHORT).show()
        } else {
            findNavControllerFromFragmentContainer(R.id.fragment_container).navigateUp()
            //stepper.goToPreviousStep()
        }
    }

    /**
     * Use navigation controller to navigate up.
     */
    override fun onSupportNavigateUp(): Boolean =
        findNavControllerFromFragmentContainer(R.id.fragment_container).navigateUp()

    fun nextPage() {
        //stepper.setImageResource(int)
        stepper.goToNextStep()
    }



    override fun onCompleted() {
        showToast("HIV Testing completed")
    }

    override fun onStepChanged(step: Int) {
        //showToast("Step changed to: $step")
        if (step == 3) {
            ll_back.visibility = View.GONE
            ll_back_white.visibility = View.VISIBLE
        } else {
            ll_back.visibility = View.VISIBLE
            ll_back_white.visibility = View.GONE
        }
    }
}