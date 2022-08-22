/**
 * Custom Progress Dialog
 * specify with PayBack Logo
 * 2022-06-18 11:00
 */
package com.coding.sixt.utilitiy

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import com.bumptech.glide.Glide
import com.coding.sixt.R
import kotlinx.android.synthetic.main.progress_dialog_view.view.*

class SIXTProgressDialog {

     lateinit var dialog: CustomDialog
    @SuppressLint("InflateParams")
    fun show(context: Context): Dialog {
        val inflater = (context as Activity).layoutInflater
        val view = inflater.inflate(R.layout.progress_dialog_view, null)
        Glide.with(context).load(R.drawable.loading).into(view.progressbar1)
        dialog = CustomDialog(context)
        dialog.setContentView(view)
        dialog.show()
        return dialog
    }



    class CustomDialog(context: Context) : Dialog(context, R.style.CustomDialogTheme) {

        init {
            window?.decorView?.rootView?.setBackgroundResource(android.R.color.transparent)
        }

    }

}