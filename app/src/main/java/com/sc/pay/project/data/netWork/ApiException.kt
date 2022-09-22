package com.sc.pay.project.data.netWork

import com.google.gson.JsonParseException
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import com.sc.pay.project.base.BaseResponse
import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 *    author : 桶哥二号
 *    motto : Anything is possible
 *    date   : 2022/9/22  4:57 下午
 *    desc   : 我好难呀，我太难了呀
 *    version: 1.0
 */

class ApiException(val code: Int, override val message: String?, override val cause: Throwable? = null)
    : RuntimeException(message, cause) {
    companion object {
        const val CODE_NET_ERROR = 4000
        const val CODE_TIMEOUT = 4080
        const val CODE_JSON_PARSE_ERROR = 4010
        const val CODE_SERVER_ERROR = 5000

        fun build(e: Throwable): ApiException {
            return if (e is HttpException) {
                ApiException(CODE_NET_ERROR, "网络异常(${e.code()},${e.message()})")
            } else if (e is UnknownHostException) {
                ApiException(CODE_NET_ERROR, "网络连接失败，请检查后再试")
            } else if (e is ConnectTimeoutException || e is SocketTimeoutException) {
                ApiException(CODE_TIMEOUT, "请求超时，请稍后再试")
            } else if (e is IOException) {
                ApiException(CODE_NET_ERROR, "网络异常(${e.message})")
            } else if (e is JsonParseException || e is JSONException) {
                ApiException(CODE_JSON_PARSE_ERROR, "数据解析错误，请稍后再试")
            } else {
                ApiException(CODE_SERVER_ERROR, "系统错误(${e.message})")
            }
        }
    }
    fun <T> toResponse(): BaseResponse<T> {
        return BaseResponse(code, message)
    }
}
