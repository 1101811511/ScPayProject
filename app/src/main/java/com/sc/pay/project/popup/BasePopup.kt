package com.sc.pay.project.popup

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import com.sc.pay.project.R
import razerdp.basepopup.BasePopupWindow

/**
 *    author : 桶哥二号
 *    motto : Anything is possible
 *    date   : 2022/9/23  1:50 下午
 *    desc   : 我好难呀，我太难了呀
 *    version: 1.0
 */
open class BasePopup (context: Context) : BasePopupWindow(context) {

    private val mHandler = Handler(Looper.getMainLooper())
    private var dismissWithRunnable: Runnable? = null
    private var mMap: Map<String, Any>? = null

    open fun setAnimationBottom(): BasePopupWindow? {
        showAnimation = AnimationUtils.loadAnimation(context, R.anim.dialog_bottom_in)
        dismissAnimation = AnimationUtils.loadAnimation(context, R.anim.dialog_bottom_out)
        return this
    }

    open fun setAnimationScale(): BasePopupWindow? {
        showAnimation = AnimationUtils.loadAnimation(context, R.anim.popup_scale_in)
        dismissAnimation = AnimationUtils.loadAnimation(context, R.anim.popup_scale_out)
        return this
    }

    open fun setCustomDataMap(map: Map<String, Any>?): BasePopupWindow? {
        mMap = map
        return this
    }

    /**
     * 延迟关闭100毫秒
     */
    open fun delayDismiss() {
        delayDismiss(100)
    }

    /**
     * 延迟关闭
     */
    open fun delayDismiss(delay: Long) {
        var d = delay
        if (d < 0) d = 0
        mHandler.postDelayed({
            dismiss()
            if (dismissWithRunnable != null) {
                dismissWithRunnable?.run()
                dismissWithRunnable = null
            }
        }, d)
    }

    /**
     * 延迟关闭 关闭之后的操作
     */
    open fun delayDismissWith(delay: Long, dismissWithRunnable: Runnable?) {
        this.dismissWithRunnable = dismissWithRunnable
        delayDismiss(delay)
    }
}