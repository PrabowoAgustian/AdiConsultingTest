package com.dirumahajanews.base.view.dialog

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.Nullable
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.dirumahajanews.R

open class BaseDialog : DialogFragment() {

    private val dialogLayoutParams = WindowManager.LayoutParams()
    var title: String = "Info"
    var description: String = ""
    var okLabel = "OK"
    var cancelLabel = "NO"
    var isHideCancelButton = true
    var okListener: View.OnClickListener? = null
    var cancelListener: View.OnClickListener? = null
    private lateinit var unbinder: Unbinder
    private var isShown = false

    @Nullable
    @BindView(R.id.titleDialog)
    @JvmField
    var titleDialog: AppCompatTextView? = null
    @Nullable
    @BindView(R.id.descriptionDialog)
    @JvmField
    var descriptionDialog: AppCompatTextView? = null
    @Nullable
    @BindView(R.id.cancelButton)
    @JvmField
    var cancelButton: AppCompatTextView? = null
    @Nullable
    @BindView(R.id.okButton)
    @JvmField
    var okButton: AppCompatTextView? = null

    override fun onStart() {
        super.onStart()
        if (dialog != null && dialog!!.window != null && dialog!!.window!!.attributes != null) {
            dialogLayoutParams.copyFrom(dialog!!.window!!.attributes)
            dialogLayoutParams.width =
                resources.getDimensionPixelOffset(R.dimen.dialog_container_width)
            dialog!!.window!!.attributes = dialogLayoutParams
            dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val builder = AlertDialog.Builder(activity)

        return getInflate(inflater, container, builder)
    }

    private fun getInflate(
        inflater: LayoutInflater,
        container: ViewGroup?,
        builder: AlertDialog.Builder
    ): View {
        val viewDialog = inflater.inflate(getLayout(), container)
        builder.setView(viewDialog)
        isCancelable = false
        unbinder = ButterKnife.bind(this, viewDialog)

        titleDialog?.text = title
        descriptionDialog?.text = description
        okButton?.text = okLabel
        okButton?.setOnClickListener(okListener ?: View.OnClickListener {
            dismissAllowingStateLoss()
        })
        cancelButton?.text = cancelLabel
        cancelButton?.setOnClickListener(cancelListener ?: View.OnClickListener {
            dismissAllowingStateLoss()
        })
        cancelButton?.visibility = if (isHideCancelButton) View.GONE else View.VISIBLE

        return viewDialog
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unbinder.unbind()
    }

    fun showAllowingStateLoss(manager: FragmentManager, tag: String) {
        if (!isShown) {
            isShown = true
            val ft = manager.beginTransaction()
            ft.add(this, tag)
            ft.commitAllowingStateLoss()
        }
    }

    override fun dismissAllowingStateLoss() {
        if (isShown) {
            isShown = false
            super.dismissAllowingStateLoss()
        }
    }

    fun enableButton(isEnable: Boolean) {
        okButton?.isEnabled = isEnable
        cancelButton?.isEnabled = isEnable
    }

    open fun getLayout(): Int {
        return R.layout.dialog_base
    }

    fun changeTitle(title: String) {
        titleDialog?.text = title
    }

}