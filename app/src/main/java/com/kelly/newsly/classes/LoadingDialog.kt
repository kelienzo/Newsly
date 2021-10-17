package com.kelly.newsly.classes

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import com.kelly.newsly.R

class LoadingDialog(private val activity: Activity) {
    private lateinit var loadingDialog: AlertDialog

    @SuppressLint("InflateParams")
    fun loadingStart() {
        val loading = activity.layoutInflater.inflate(R.layout.loading_dialog, null)
        val builder = AlertDialog.Builder(activity)

        with(builder) {
            setView(loading)
            setCancelable(true)
        }
        loadingDialog = builder.create()
        loadingDialog.show()
    }

    fun loadingEnd() {
        loadingDialog.dismiss()
    }
}