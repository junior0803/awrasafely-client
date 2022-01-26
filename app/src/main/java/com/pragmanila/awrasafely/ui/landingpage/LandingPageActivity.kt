package com.pragmanila.awrasafely.ui.landingpage

import android.app.ActionBar
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.navigation.NavigationView
import com.pragmanila.awrasafely.R
import com.pragmanila.awrasafely.adapter.LandingPageAdapter
import com.pragmanila.awrasafely.api.model.landingpage.LandingPage
import com.pragmanila.awrasafely.ui.arv.ARVRequestActivity
import com.pragmanila.awrasafely.ui.fcl.FCLRequestActivity
import com.pragmanila.awrasafely.ui.preprequest.PrepRequestActivity
import com.pragmanila.awrasafely.ui.testingservices.TestingServicesActivity
import com.pragmanila.awrasafely.ui.testingservices.TestingServicesTabsActivity
import kotlinx.android.synthetic.main.landingpage.*
import kotlinx.android.synthetic.main.landingpage_layout.*

class LandingPageActivity : AppCompatActivity() {

    private lateinit var landingPageAdapter: LandingPageAdapter
    private var dataList = mutableListOf<LandingPage>()
    lateinit var toggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.landingpage)

        setSupportActionBar(toolbar)
        ViewCompat.setLayoutDirection(toolbar, ViewCompat.LAYOUT_DIRECTION_RTL)

        val toggle = ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        toggle.isDrawerIndicatorEnabled=true
        drawerLayout.addDrawerListener(toggle)

        if (drawerLayout != null && drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawer(GravityCompat.END);
        }
        toggle.syncState()

        lp_ll_testing.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@LandingPageActivity, TestingServicesActivity::class.java))
            finish()
        })

        lp_ll_prep.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@LandingPageActivity, PrepRequestActivity::class.java))
            finish()
        })

        lp_ll_fcl.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@LandingPageActivity, FCLRequestActivity::class.java))
            finish()
        })

        lp_ll_arv.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@LandingPageActivity, ARVRequestActivity::class.java))
            finish()
        })

    }
}