package com.sc.pay.project.widget

import android.util.Log
import com.sc.pay.project.base.BaseResponse
import com.sc.pay.project.data.netWork.ApiException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *    author : 桶哥二号
 *    motto : Anything is possible
 *    date   : 2022/9/22  2:05 下午
 *    desc   : 我好难呀，我太难了呀
 *    version: 1.0
 */


suspend inline fun <T> apiCall(crossinline call: suspend CoroutineScope.() -> BaseResponse<T>): BaseResponse<T> {
    return withContext(Dispatchers.IO) {
        val res: BaseResponse<T>
        try {
            res = call()
        } catch (e: Throwable) {
            return@withContext ApiException.build(e).toResponse<T>()
        }
        return@withContext res
    }
}


