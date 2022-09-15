package com.sc.pay.project.base

/**
 * author : 桶哥二号
 * motto  : 又是一个坑
 * date   : 2022/9/15.
 */
data class BaseResponse<T>(
    val data: T,
    val errorCode: Int,
    val errorMsg: String
)
