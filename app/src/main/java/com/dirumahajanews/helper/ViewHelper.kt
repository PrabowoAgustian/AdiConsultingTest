package com.dirumahajanews.helper

import android.view.View

@Suppress("DEPRECATION")
class ViewHelper {

    companion object {

        fun showView(view: View?) {
            if (view != null)
                view.visibility = View.VISIBLE
        }
    }
}