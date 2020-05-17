package com.mauricioburgos.pokeyellow.presentation.view.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mauricioburgos.pokeyellow.R
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }




    fun changeToolbarText(toolbarText: String){
        toolbarTextView.text = toolbarText
    }
}
