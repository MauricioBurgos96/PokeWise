package com.mauricioburgos.pokeyellow.presentation.view.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.mauricioburgos.pokeyellow.R
import com.mauricioburgos.pokeyellow.core.utils.FontSingleton
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        toolbarTextView.typeface= FontSingleton.fontBold

        setUpNavigation()
    }


    fun setUpNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        NavigationUI.setupWithNavController(
            bottomNavMain,
            navHostFragment!!.navController
        )
    }


    fun changeToolbarText(toolbarText: String){
        toolbarTextView.text = toolbarText
    }
}
