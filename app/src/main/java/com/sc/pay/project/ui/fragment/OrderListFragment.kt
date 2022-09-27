package com.sc.pay.project.ui.fragment

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingSource
import com.sc.pay.project.R
import com.sc.pay.project.base.BaseFragment
import com.sc.pay.project.databinding.FragmentOrderListBinding
import com.sc.pay.project.db.OrderDatabase
import com.sc.pay.project.db.OrderEntity
import com.sc.pay.project.viewModel.OrderListFragmentViewModel

/**
 *    author : 桶哥二号
 *    motto : Anything is possible
 *    date   : 2022/9/23  3:22 下午
 *    desc   : 我好难呀，我太难了呀
 *    version: 1.0
 */
class OrderListFragment :BaseFragment<FragmentOrderListBinding,OrderListFragmentViewModel>(OrderListFragmentViewModel::class.java){
    val database: OrderDatabase = OrderDatabase.dataBase
    override fun initView() {
       val   order = database.getUserDao().getOrderRecord("123123")
        Log.i("1123123",order?.payMoney.toString())

    }

    override fun getLayoutId() = R.layout.fragment_order_list
}