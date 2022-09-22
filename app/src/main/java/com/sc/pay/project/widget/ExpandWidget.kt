package com.sc.pay.project.widget

import android.widget.TextView
import android.widget.Toast
import com.sc.pay.project.ScApplicaiton

/**
 * author : 桶哥二号
 * motto  : 又是一个坑
 * date   : 2022/9/15.
 */

fun TextView.checkContent(errorInfo: String?):String? {
    val content = text.toString()
    if (content.isBlank()){
        errorInfo?.showMsg()
        return null
    }
    return content
}

fun String.showMsg(duration:Int =Toast.LENGTH_SHORT){
    Toast.makeText(ScApplicaiton.applicationContent,this,Toast.LENGTH_SHORT).show()
}