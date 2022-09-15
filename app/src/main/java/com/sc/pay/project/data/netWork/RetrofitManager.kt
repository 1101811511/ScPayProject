package com.sc.pay.project.data.netWork

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import java.util.logging.Level

/**
 * author : 桶哥二号
 * motto  : 又是一个坑
 * date   : 2022/9/15.
 */
object RetrofitManager {


    /**
     * 配置Retrofit
     */
    fun <T> creatApiServices(classes: Class<T>): T {
        return Retrofit.Builder().baseUrl("https://www.wanandroid.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okhhtClient)
            .build().create(classes)
    }



    /**
     * 初始话okhttpClient
     */
    private val okhhtClient: OkHttpClient
        get() {
            return OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(mInterceptorLog)
                .build()
        }

    /**
     * 日志拦截器
     */
    private val mInterceptorLog: Interceptor
        get() {
            return HttpLoggingInterceptor("scproject").apply {
                setPrintLevel(HttpLoggingInterceptor.Level.BODY)
                setColorLevel(Level.INFO)
            }
        }


}