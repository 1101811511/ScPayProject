package com.sc.pay.project.base

import com.google.gson.annotations.SerializedName

/**
 * author : 桶哥二号
 * motto  : 又是一个坑
 * date   : 2022/9/15.
 */
data class BaseResponse<T>( @SerializedName("errorCode") val errorCode: Int=-1,@SerializedName("errorMsg") val errorMsg: String?="",@SerializedName("data") val data: T?=null)
