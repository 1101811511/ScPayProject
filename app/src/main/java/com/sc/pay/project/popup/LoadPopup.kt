package com.sc.pay.project.popup

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import com.sc.pay.project.R

/**
 *    author : 桶哥二号
 *    motto : Anything is possible
 *    date   : 2022/9/23  1:55 下午
 *    desc   : 我好难呀，我太难了呀
 *    version: 1.0
 */
class LoadPopup(context: Context) : BasePopup(context) {

    init {
        setBackgroundColor(Color.TRANSPARENT)
        setContentView(R.layout.popup_loading)
        popupGravity = Gravity.CENTER
        setOutSideDismiss(false)
        isOutSideTouchable = false
        showPopupWindow()
    }
}