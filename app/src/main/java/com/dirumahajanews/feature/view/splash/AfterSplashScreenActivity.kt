package com.dirumahajanews.feature.view.splash

import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import com.dirumahajanews.base.view.activity.BaseActivity
import com.dirumahajanews.feature.view.main.HomeActivity
import com.dirumahajanews.R

class AfterSplashScreenActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        super.onCreate(savedInstanceState)
    }

    override fun layoutRes(): Int {
        return R.layout.activity_splash_screen
    }

    override fun onResume() {
        super.onResume()
        goToHomeActivity()
    }

    private fun goToHomeActivity() {
        Handler().postDelayed({
            showActivityAndFinishAllActivity(this, HomeActivity::class.java)
            finish()
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }, 5000)
    }

}