package com.sc.pay.project.db

import androidx.room.Entity

/**
 *    author : 桶哥二号
 *    motto : Anything is possible
 *    date   : 2022/9/23  3:46 下午
 *    desc   : 我好难呀，我太难了呀
 *    version: 1.0
 */
@Entity(tableName = "OrderRecord")
data class OrderEntity(
    var tradeTime: String,//时间
    var showTime: String,//时间
    var commitStatus: String,//提交状态
    var payMoney: String,//交易金额
    var outOrderNum: String//外部商户订单号
)

