package com.sc.pay.project.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 *    author : 桶哥二号
 *    motto : Anything is possible
 *    date   : 2022/9/23  5:47 下午
 *    desc   : 我好难呀，我太难了呀
 *    version: 1.0
 */
@Dao
interface OrderDao {
    /**
     * 插入一条数据
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(orderEntity: OrderEntity)

    /**
     * 根据订单号查询
     */
    @Query("select * from OrderRecord")
    suspend fun getAllUser(): MutableList<OrderEntity>?

}