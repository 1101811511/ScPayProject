package com.sc.pay.project.ui.fragment

import com.sc.pay.project.R
import com.sc.pay.project.base.BaseFragment
import com.sc.pay.project.databinding.FragmentOrderInputBinding
import com.sc.pay.project.viewModel.OrderFragmentViewModel

/**
 * author : 桶哥二号
 * motto  : 又是一个坑
 * date   : 2022/9/13.
 */
class InputOrderFragment:BaseFragment<FragmentOrderInputBinding,OrderFragmentViewModel>(OrderFragmentViewModel::class.java) {
    override fun getLayoutId() = R.layout.fragment_order_input

    override fun initView() {
        db.orderModel =viewModel
    }

}