package com.mauricioburgos.pokewise.presentation.view.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.mauricioburgos.pokewise.R
import com.mauricioburgos.pokewise.core.utils.FontSingleton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*

@AndroidEntryPoint
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
