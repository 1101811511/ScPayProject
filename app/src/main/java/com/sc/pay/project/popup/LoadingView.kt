package com.sc.pay.project.popup

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.Gravity
import com.sc.pay.project.R

/**
 *    author : 桶哥二号
 *    motto : Anything is possible
 *    date   : 2022/9/23  1:54 下午
 *    desc   : 我好难呀，我太难了呀
 *    version: 1.0
 */
class LoadingView private constructor(context: Context) : BasePopup(context){

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var instance: LoadingView? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: LoadingView(context).also { instance = it }
            }
    }

    init {
        setBackgroundColor(Color.TRANSPARENT)
        setContentView(R.layout.popup_loading)
        popupGravity = Gravity.CENTER
        setOutSideDismiss(false)
        isOutSideTouchable = false
    }
}