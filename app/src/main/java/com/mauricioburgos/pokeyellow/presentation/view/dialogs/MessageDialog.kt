package com.mauricioburgos.pokeyellow.presentation.view.dialogs
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.mauricioburgos.pokeyellow.R
import com.mauricioburgos.pokeyellow.core.utils.FontSingleton
import kotlinx.android.synthetic.main.dialog_message_layout.*


class MessageDialog constructor(var title: String, var subtitle: String) : DialogFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_message_layout, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog!!.window?.requestFeature(Window.FEATURE_NO_TITLE)

        tvTitle_DiagNot.text= title
        tvTitle_DiagNot.typeface= FontSingleton.fontBold
        tvSubTitle_DiagNot.text=subtitle

        tvAccept_DiagNot.setOnClickListener(clickListener)
    }

    private val clickListener = View.OnClickListener { view ->

        when (view.id) {
            R.id.tvAccept_DiagNot -> {
               dismiss()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val style = DialogFragment.STYLE_NO_FRAME
        val theme = R.style.DialogTheme
        setStyle(style, theme)
    }


    interface DialogListener {
        fun onSelectDoneDialog(notificationDialog: MessageDialog)
    }
}