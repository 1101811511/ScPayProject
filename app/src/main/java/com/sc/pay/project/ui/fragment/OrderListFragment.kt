package com.sc.pay.project.ui.fragment

import com.sc.pay.project.R
import com.sc.pay.project.base.BaseFragment
import com.sc.pay.project.databinding.FragmentOrderListBinding
import com.sc.pay.project.viewModel.OrderListFragmentViewModel

/**
 *    author : 桶哥二号
 *    motto : Anything is possible
 *    date   : 2022/9/23  3:22 下午
 *    desc   : 我好难呀，我太难了呀
 *    version: 1.0
 */
class OrderListFragment :BaseFragment<FragmentOrderListBinding,OrderListFragmentViewModel>(OrderListFragmentViewModel::class.java){
    override fun initView() {
    }

    override fun getLayoutId() = R.layout.fragment_order_list
}