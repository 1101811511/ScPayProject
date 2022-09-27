package com.sc.pay.project.data.netWork

import com.sc.pay.project.base.BaseResponse
import com.sc.pay.project.data.bean.User
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * author : 桶哥二号
 * motto  : 又是一个坑
 * date   : 2022/9/15.
 */
interface Api {
    /**
     * 查询订单信息
     */
    @FormUrlEncoded
    @POST("user/login")
    suspend fun getOrderInfo(
        @Field("username") userName: String,
        @Field("password") pwd: String
    ): BaseResponse<User>

    /**
     * 测试
     */
    @FormUrlEncoded
    @POST("user/register")
    suspend fun registUser(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") rePassword: String
    ): BaseResponse<User>


}