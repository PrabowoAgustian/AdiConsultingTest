@file:Suppress("DEPRECATION")

package com.dirumahajanews.base.data

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.dirumahajanews.BuildConfig
import com.securepreferences.SecurePreferences

@Suppress("DEPRECATION")
open class PreferenceManager (
    context: Context,
    preferenceName : String
){
    private val securePreferences: SecurePreferences = SecurePreferences(
        context,
        BuildConfig.keyPreference,
        preferenceName
    )

    private val sharedPreferences: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    /*
    write all setter below
     */
}