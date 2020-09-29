package com.mauricioburgos.pokewise.presentation.view.dialogs
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import com.mauricioburgos.pokewise.R
import com.mauricioburgos.pokewise.core.utils.FontSingleton
import com.mauricioburgos.pokewise.domain.PokemonDetails
import kotlinx.android.synthetic.main.dialog_confirm_layout.*


class ConfirmDialog constructor(val listener: DialogListener,var textConfirm: String, val pokemonDetails : PokemonDetails) : DialogFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_confirm_layout, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog!!.window?.requestFeature(Window.FEATURE_NO_TITLE)

        text_confirm.typeface= FontSingleton.fontBold

        btn_accept_confirm.setOnClickListener(clickListener)
        btn_cancel_confirm.setOnClickListener(clickListener)

    }

    private val clickListener = View.OnClickListener { view ->

        when (view.id) {
          R.id.btn_accept_confirm -> {
             dismiss()
              listener.onSelectDoneDialog(pokemonDetails)

          }

          R.id.btn_cancel_confirm -> {
              dismiss()
          }
        }
    }



    override fun onResume() {
        super.onResume()
        var params = dialog!!.window?.attributes
        params!!.width = ConstraintLayout.LayoutParams.MATCH_PARENT
        params!!.height = ConstraintLayout.LayoutParams.WRAP_CONTENT
        dialog!!.window?.attributes= params

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val style = DialogFragment.STYLE_NO_FRAME
        val theme = R.style.DialogTheme
        setStyle(style, theme)
    }


    interface DialogListener {
        fun onSelectDoneDialog(pokemonDetails : PokemonDetails)
    }
}