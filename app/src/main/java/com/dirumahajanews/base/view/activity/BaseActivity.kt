package com.dirumahajanews.base.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.ButterKnife
import com.dirumahajanews.R
import com.dirumahajanews.base.view.dialog.BaseDialog
import com.dirumahajanews.base.view.dialog.LoadingDialog
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter

abstract class BaseActivity : AppCompatActivity() {

    private var inputMode: Int? = null
    lateinit var baseDialog: BaseDialog
    private lateinit var loadingDialog: LoadingDialog
    private var useNormalBackPress = false

    protected fun setSoftInoputMode(inputMode: Int) {
        this.inputMode = inputMode
    }

    private fun getSoftInputMode(): Int {
        return inputMode ?: WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
    }

    @LayoutRes
    abstract fun layoutRes(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.window.setSoftInputMode(getSoftInputMode())
        setContentView(layoutRes())
        ButterKnife.bind(this)
        createDialog()
    }

    private fun createDialog() {
        baseDialog = BaseDialog()
        loadingDialog = LoadingDialog()
    }

    protected fun onlyShowLoading() {
        loadingDialog.showAllowingStateLoss(supportFragmentManager, "LOADING_DIALOG")
    }

    open fun showLoading() {
        loadingDialog.title = "Sedang memuat data"
        onlyShowLoading()
    }

    protected fun showLoading(title: String) {
        loadingDialog.title = title
        onlyShowLoading()
    }

    protected fun changeLoadingTitle(title: String) {
        loadingDialog.changeTitle(title)
    }
    private fun showBaseDialog(
        title: String,
        description: String
    ) {
        baseDialog.title = title
        baseDialog.description = description
        baseDialog.showAllowingStateLoss(supportFragmentManager, "BASE_DIALOG")
    }

    private fun showBaseDialog(
        title: String,
        description: String,
        okListener: View.OnClickListener
    ) {
        baseDialog.okListener = okListener
        showBaseDialog(title, description)
    }

    protected fun showInfoDialog(message: String) {
        baseDialog.okListener = View.OnClickListener {
            baseDialog.dismissAllowingStateLoss()
        }
        baseDialog.isHideCancelButton = true
        showBaseDialog("Informasi", message)
    }

    protected fun showInfoNoCancelDialog(
        title: String,
        message: String,
        okLabel: String,
        okClickListener: View.OnClickListener
    ) {
        baseDialog.okLabel = okLabel
        baseDialog.okListener = okClickListener
        baseDialog.isHideCancelButton = true
        showBaseDialog(title, message)
    }

    protected fun showInfoWithCancelDialog(
        title: String,
        message: String,
        okLabel: String,
        cancelLabel: String,
        okClickListener: View.OnClickListener,
        cancelClickListener: View.OnClickListener
    ) {
        baseDialog.okLabel = okLabel
        baseDialog.cancelLabel = cancelLabel
        baseDialog.okListener = okClickListener
        baseDialog.cancelListener = cancelClickListener
        baseDialog.isHideCancelButton = false
        showBaseDialog(title, message)
    }

    protected fun showErrorDialog(message: String) {
        baseDialog.isHideCancelButton = true
        showBaseDialog("Error", message)
    }

    protected fun showConfirmationDialog(message: String) {
        baseDialog.isHideCancelButton = false
        showBaseDialog("Konfirmasi", message)
    }

    protected fun showConfirmationDialog(message: String, okListener: View.OnClickListener) {
        baseDialog.okListener = okListener
        showConfirmationDialog(message)
    }

    protected fun showSuccessDialog(message: String, okListener: View.OnClickListener) {
        baseDialog.isHideCancelButton = true
        showBaseDialog("Sukses", message, okListener)
    }

    protected fun dismissBaseDialog() {
        baseDialog.dismissAllowingStateLoss()
    }

    open fun dismissLoading() {
        loadingDialog.dismissAllowingStateLoss()
    }

    protected fun configureItemAdapter(
        recyclerView: RecyclerView,
        adapter: FastItemAdapter<*>
    ) {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        recyclerView.isNestedScrollingEnabled = false
        if (!adapter.isSelectable)
            adapter.withSelectable(true)
    }

    fun configureHorizontalItemAdapter(
        recyclerView: RecyclerView,
        adapter: FastItemAdapter<*>
    ) {
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter
        recyclerView.isNestedScrollingEnabled = false
        if (!adapter.isSelectable)
            adapter.withSelectable(true)
    }

    fun configureGridItemAdapter(
        recyclerView: RecyclerView,
        adapter: FastItemAdapter<*>,
        span: Int
    ) {
        recyclerView.layoutManager = GridLayoutManager(this, span)
        recyclerView.adapter = adapter
        recyclerView.isNestedScrollingEnabled = false
        if (!adapter.isSelectable)
            adapter.withSelectable(true)
    }

    protected fun showActivity(intent: Intent) {
        startActivity(intent)
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
    }

    protected fun showActivity(context: Context, cls: Class<*>) {
        showActivity(Intent(context, cls))
    }

    protected fun showActivityAndFinishCurrent(intent: Intent) {
        showActivity(intent)
        finish()
    }

    protected fun showActivityAndFinishAllActivity(context: Context, cls: Class<*>) {
        val intent = getIntent(context, cls)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        showActivityAndFinishCurrent(intent)
    }

    protected open fun showActivityNoAnimationAndFinishCurrentActivity(
        packageContext: Context?,
        cls: Class<*>?
    ) {
        val intent = getIntent(packageContext!!, cls!!)
        intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
        startActivity(intent)
        overridePendingTransition(0, 0)
        finish()
    }

    protected fun getIntent(context: Context, cls: Class<*>): Intent {
        return Intent(context, cls)
    }

    protected fun finishActivity() {
        finish()
        overridePendingTransition(R.anim.exit_to_right, R.anim.enter_from_left)
    }

    protected fun setUseNormalBackPress(useNormalBackPress: Boolean) {
        this.useNormalBackPress = useNormalBackPress
    }

    override fun onBackPressed() {
        if (useNormalBackPress)
            super.onBackPressed()
        else
            finishActivity()
    }

    protected fun showActivityForResult(intent: Intent, requestCode: Int) {
        startActivityForResult(intent, requestCode)
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
    }

    protected fun finishActivityForResult(intent: Intent, requestCode: Int) {
        setResult(requestCode, intent)
        finishActivity()
    }

    protected fun dismissKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(window.decorView.windowToken, 0)
    }

    protected fun configureBackPress() {
        findViewById<View>(R.id.backButton)?.setOnClickListener {
            onBackPressed()
        }
    }

    protected fun disableClick() {
        window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
    }

    protected fun enableClick() {
        window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }
}