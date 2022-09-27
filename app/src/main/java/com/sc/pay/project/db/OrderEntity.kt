package com.sc.pay.project.db


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

/**
 *    author : 桶哥二号
 *    motto : Anything is possible
 *    date   : 2022/9/23  3:46 下午
 *    desc   : 我好难呀，我太难了呀
 *    version: 1.0
 */
@Entity(tableName = "OrderRecord" ,primaryKeys =["orderNum"] )
data class OrderEntity(
    @ColumnInfo(name = "tradeTime")
    val tradeTime: String?,//时间
    @ColumnInfo(name = "showTime")
    val showTime: String?,//时间
    @ColumnInfo(name = "commitStatus")
    val commitStatus: String?,//提交状态
    @ColumnInfo(name = "payMoney")
    val payMoney: String?,//交易金额
    @ColumnInfo(name = "outOrderNum")
    val outOrderNum: String?,//外部商户订单号
    @ColumnInfo(name = "orderNum")
    val orderNum:String //订单号

)

