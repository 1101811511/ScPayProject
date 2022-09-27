package com.sc.pay.project.repository

import com.sc.pay.project.base.BaseResponse
import com.sc.pay.project.data.bean.User
import com.sc.pay.project.data.netWork.Api
import com.sc.pay.project.data.netWork.RetrofitManager

/**
 * author : 桶哥二号
 * motto  : 又是一个坑
 * date   : 2022/9/15.
 */
class OrderNumRepository {
    //查询订单信息
    suspend fun getOrderInfo(userName: String, pwd: String): BaseResponse<User> =
        RetrofitManager.creatApiServices(Api::class.java).getOrderInfo(userName, pwd)

    //测试接口
    suspend fun registUser(userName: String, pwd: String, rePwd: String): BaseResponse<User> =
        RetrofitManager.creatApiServices(Api::class.java).registUser(userName, pwd, rePwd)

}